package br.com.Portifolio.Room.Reservation.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum RoomStatus {
    @JsonProperty("available")
    AVAILABLE,
    @JsonProperty("occupied")
    OCCUPIED,
    @JsonProperty("inactive")
    INACTIVE,
    DEFAULT
}
