package br.com.Portifolio.Room.Reservation.dto;

import br.com.Portifolio.Room.Reservation.model.ReservationStatus;
import br.com.Portifolio.Room.Reservation.model.Room;
import org.jspecify.annotations.NonNull;

import java.time.LocalDate;
import java.time.Period;

public record ReservationDto(@NonNull Integer reservationNumber,
                             Period period,
                             LocalDate start,
                             LocalDate end,
                             Room room,
                             ReservationStatus status) {
}
