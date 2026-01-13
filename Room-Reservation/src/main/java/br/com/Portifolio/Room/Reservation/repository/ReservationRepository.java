package br.com.Portifolio.Room.Reservation.repository;

import br.com.Portifolio.Room.Reservation.dto.RoomDto;
import br.com.Portifolio.Room.Reservation.model.Reservation;
import br.com.Portifolio.Room.Reservation.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    RoomDto save(RoomDto roomDto);

    boolean existsByReservationNumber(Integer number);

    Reservation findByReservationNumber(Integer reservationNumber);

    Reservation findByRoom(Room room);
}
