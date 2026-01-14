package br.com.Portifolio.Room.Reservation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDto(@NotBlank String name,
                      @NotBlank String cpf,
                      @NotNull Integer age,
                      @NotBlank String cityOrigin) {
}
