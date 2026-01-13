package br.com.Portifolio.Room.Reservation.model;

import br.com.Portifolio.Room.Reservation.dto.RoomDto;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //Identify the room
    private Integer capacity; //How many people the room suportted
    private RoomType type; //the type of the room
    private BigDecimal dailyPrice; //How much this room cost
    private Integer number;
    @Enumerated
    private RoomStatus status;//Tell to the client the status of the room(Available or ocuppied)

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Reservation reservation;

    public Room(){}

    public Room(RoomDto dto){ //Use Data transfer Object
        this.number = dto.number();
        this.capacity = dto.capacity();
        this.type = dto.type();
        this.dailyPrice = dto.dailyPrice();
        this.status = dto.status();
    }

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public BigDecimal getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(BigDecimal dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
