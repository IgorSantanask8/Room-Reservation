package br.com.Portifolio.Room.Reservation.dto;

import jakarta.validation.constraints.NotNull;

public record UserDto(@NotNull String name,
                      @NotNull String cpf,
                      @NotNull Integer age,
                      @NotNull String cityOrigin) {
}
