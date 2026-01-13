package br.com.Portifolio.Room.Reservation.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum RoomType {
    @JsonProperty("basic")
    BASIC,
    @JsonProperty("premium")
    PREMIUM,
    @JsonProperty("ultra")
    ULTRA
}
