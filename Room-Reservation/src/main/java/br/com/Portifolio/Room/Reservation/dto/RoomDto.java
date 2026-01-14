package br.com.Portifolio.Room.Reservation.dto;

import br.com.Portifolio.Room.Reservation.model.RoomStatus;
import br.com.Portifolio.Room.Reservation.model.RoomType;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record RoomDto(@NotNull Integer number,
                      @NotNull Integer capacity,
                      @NotNull RoomType type,
                      @NotNull BigDecimal dailyPrice,
                      @NotNull RoomStatus status) {
}
