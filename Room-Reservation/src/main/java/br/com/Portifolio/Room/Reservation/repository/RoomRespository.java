package br.com.Portifolio.Room.Reservation.repository;

import br.com.Portifolio.Room.Reservation.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRespository extends JpaRepository<Room,Long> {

    boolean existsByNumber(Integer number);

    Room findByNumber(Integer number);

    Room getReferenceByNumber(Integer number);

}
