package br.com.Portifolio.Room.Reservation.service;

import br.com.Portifolio.Room.Reservation.dto.ReservationDto;
import br.com.Portifolio.Room.Reservation.exceptions.ReservationNotFoundException;
import br.com.Portifolio.Room.Reservation.model.Reservation;
import br.com.Portifolio.Room.Reservation.model.ReservationStatus;
import br.com.Portifolio.Room.Reservation.model.Room;
import br.com.Portifolio.Room.Reservation.model.User;
import br.com.Portifolio.Room.Reservation.repository.ReservationRepository;
import br.com.Portifolio.Room.Reservation.repository.RoomRespository;
import br.com.Portifolio.Room.Reservation.repository.UserRepository;
import br.com.Portifolio.Room.Reservation.validations.ValidadorReserva;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock
    private ReservationRepository repository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoomRespository roomRespository;
    @InjectMocks
    private ReservationService service;
    @Captor
    private ArgumentCaptor<Reservation> reservationArgumentCaptor;
    @Spy
    private Reservation reservation;
    @Spy
    private Room room;
    @Spy
    private User user;
    @Spy
    private List<ValidadorReserva> validators = new ArrayList<>();
    @Spy
    private ObjectMapper mapper = new ObjectMapper();


    @Test
    @DisplayName("Should create Reservation")
    void scenario01() throws IOException {
        ReservationDto dto = new ReservationDto(1, Period.between(LocalDate.now(),LocalDate.now().plusWeeks(1)),
                LocalDate.now(),LocalDate.now().plusWeeks(1),
                room, ReservationStatus.ACTIVE);
        reservation = new Reservation(dto);

        BDDMockito.given(userRepository.existsByCpf(user.getCpf())).willReturn(true);
        BDDMockito.given(userRepository.findByCpf(user.getCpf())).willReturn(user);

        BDDMockito.given(roomRespository.existsByNumber(room.getNumber())).willReturn(true);
        BDDMockito.given(roomRespository.findByNumber(room.getNumber())).willReturn(room);


        service.createReservation(user.getCpf(),room.getNumber(),dto);

        then(repository).should().save(reservationArgumentCaptor.capture());
        Reservation reservationSave = reservationArgumentCaptor.getValue();

        Assertions.assertEquals(dto.start(),reservationSave.getStart());
        Assertions.assertEquals(dto.end(),reservationSave.getEnd());
        Assertions.assertEquals(dto.reservationNumber(),reservationSave.getReservationNumber());
        Assertions.assertEquals(dto.status(),reservationSave.getStatus());
        Assertions.assertEquals(dto.room(),reservationSave.getRoom());
        Assertions.assertEquals(dto.period(),reservationSave.getPeriod());
    }

    @Test
    @DisplayName("Should find Reservation")
    void scenario02(){
        ReservationDto dto = new ReservationDto(1, Period.between(LocalDate.now(),LocalDate.now().plusWeeks(1)),
                LocalDate.now(),LocalDate.now().plusWeeks(1),
                room, ReservationStatus.ACTIVE);
        reservation = new Reservation(dto);

        BDDMockito.given(repository.existsByReservationNumber(reservation.getReservationNumber())).willReturn(true);
        BDDMockito.given(repository.findByReservationNumber(reservation.getReservationNumber())).willReturn(reservation);

        Assertions.assertDoesNotThrow(() -> service.searchReservationByNumber(reservation.getReservationNumber()));
    }

    @Test
    @DisplayName("Should not find Reservation")
    void scenario03(){
        ReservationDto dto = new ReservationDto(1, Period.between(LocalDate.now(),LocalDate.now().plusWeeks(1)),
                LocalDate.now(),LocalDate.now().plusWeeks(1),
                room, ReservationStatus.ACTIVE);
        reservation = new Reservation(dto);

        BDDMockito.given(repository.existsByReservationNumber(reservation.getReservationNumber())).willReturn(false);

        Assertions.assertThrows(ReservationNotFoundException.class,() -> service.searchReservationByNumber(reservation.getReservationNumber()));
    }

    @Test
    @DisplayName("should delete reservation")
    void scenario04(){
        ReservationDto dto = new ReservationDto(1, Period.between(LocalDate.now(),LocalDate.now().plusWeeks(1)),
                LocalDate.now(),LocalDate.now().plusWeeks(1),
                room, ReservationStatus.ACTIVE);
        reservation = new Reservation(dto);

        BDDMockito.given(repository.existsByReservationNumber(reservation.getReservationNumber())).willReturn(true);
        BDDMockito.given(repository.findByReservationNumber(reservation.getReservationNumber())).willReturn(reservation);

        service.cancelReservation(reservation.getReservationNumber());

        then(repository).should().delete(reservationArgumentCaptor.capture());
    }

}