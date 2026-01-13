package br.com.Portifolio.Room.Reservation.service;

import br.com.Portifolio.Room.Reservation.dto.ReservationDto;
import br.com.Portifolio.Room.Reservation.exceptions.ReservationNotFoundException;
import br.com.Portifolio.Room.Reservation.exceptions.RoomNotFoundException;
import br.com.Portifolio.Room.Reservation.model.*;
import br.com.Portifolio.Room.Reservation.repository.ReservationRepository;
import br.com.Portifolio.Room.Reservation.repository.RoomRespository;
import br.com.Portifolio.Room.Reservation.repository.UserRepository;
import br.com.Portifolio.Room.Reservation.validations.ReservationPeriodValidation;
import br.com.Portifolio.Room.Reservation.validations.RoomStatusValidation;
import br.com.Portifolio.Room.Reservation.validations.ValidadorReserva;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoomRespository roomRespository;
    @Autowired
    private List<ValidadorReserva> validators;
    @Autowired
    private ObjectMapper mapper;

    private ReservationPeriodValidation validation;
    private RoomStatusValidation roomStatusValidation;

    @Transactional
    public void createReservation(String cpf, Integer number, ReservationDto dto) throws IOException {
        if(!userRepository.existsByCpf(cpf)){
            throw new ReservationNotFoundException("This user does not exist");
        }
        if(!roomRespository.existsByNumber(number)){
            throw new RoomNotFoundException("This room does not exist");
        }
        Reservation reservation = new Reservation(dto);

        User user = userRepository.findByCpf(cpf);
        Room room = roomRespository.findByNumber(number);

        reservation.setUser(user);
        reservation.setRoom(room);

        validators.forEach(v -> v.validar(reservation));

        reservation.setStatus(ReservationStatus.ACTIVE);
        room.setStatus(RoomStatus.OCCUPIED);

        reservation.setStart(LocalDate.now());
        reservation.setEnd(reservation.getStart().plusWeeks(1));

        reservation.setPeriod(Period.between(reservation.getStart(),reservation.getEnd()));


        System.out.println("Reservation created! ");

        repository.save(reservation);

        mapper.isEnabled(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(new File("reservations.json"),reservation);
    }

    public List<ReservationDto> searchReservations() {
        return convert(repository.findAll());
    }

    public List<ReservationDto> convert(List<Reservation> reservations){
        return reservations.stream()
                .map(r -> new ReservationDto(r.getReservationNumber(),r.getPeriod(),r.getStart(),
                        r.getEnd(),r.getRoom(),r.getStatus()))
                .collect(Collectors.toList());
    }

    public Reservation searchReservationByNumber(Integer reservationNumber) {
        if(!repository.existsByReservationNumber(reservationNumber)){
            throw new ReservationNotFoundException("This reservation does not exist");
        }
        return repository.findByReservationNumber(reservationNumber);
    }

    @Transactional
    public void cancelReservation(Integer reservationNumber) {
        if(!repository.existsByReservationNumber(reservationNumber)){
            throw new ReservationNotFoundException("This reservation does not exist");
        }
        Reservation reservation = repository.findByReservationNumber(reservationNumber);

        Room room = reservation.getRoom();
        room.setStatus(RoomStatus.AVAILABLE);

        reservation.setStatus(ReservationStatus.INACTIVE);

        repository.delete(reservation);
    }

    @Transactional
    public Reservation changeRoom(Integer reservationNumber, Integer number, Integer number2) {
        Reservation reservation = repository.findByReservationNumber(reservationNumber);
        Room room = roomRespository.findByNumber(number);
        User user = reservation.getUser();

        if(!roomRespository.existsByNumber(number2)){
            throw new RoomNotFoundException("This room does not exist");
        }
        if(!repository.existsByReservationNumber(reservationNumber)){
            throw new ReservationNotFoundException("This reservation does not exist");
        }
        Room r = roomRespository.findByNumber(number2);

        reservation.setRoom(r);

        room.setStatus(RoomStatus.AVAILABLE);
        r.setStatus(RoomStatus.OCCUPIED);

        validators.forEach(v -> v.validar(reservation));

        roomRespository.save(room);
        roomRespository.save(r);

        return repository.save(reservation);
    }

    public Reservation searchReservationByRoom(Integer number) {

        if(!roomRespository.existsByNumber(number)){
            throw new RoomNotFoundException("This room doesn not exist");
        }

        Room room = roomRespository.findByNumber(number);
        return repository.findByRoom(room);

    }
}
