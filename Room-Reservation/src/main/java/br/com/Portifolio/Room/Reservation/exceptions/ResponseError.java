package br.com.Portifolio.Room.Reservation.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ResponseError(
        String menssage,
        HttpStatus httpStatus,
        LocalDateTime now
) {
}
