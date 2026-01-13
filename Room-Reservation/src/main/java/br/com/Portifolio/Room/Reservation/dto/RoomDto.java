package br.com.Portifolio.Room.Reservation.dto;

import br.com.Portifolio.Room.Reservation.model.RoomStatus;
import br.com.Portifolio.Room.Reservation.model.RoomType;
import org.jspecify.annotations.NonNull;

import java.math.BigDecimal;

public record RoomDto(@NonNull Integer number,
                      @NonNull Integer capacity,
                      @NonNull RoomType type,
                      @NonNull BigDecimal dailyPrice,
                      @NonNull RoomStatus status) {
}
