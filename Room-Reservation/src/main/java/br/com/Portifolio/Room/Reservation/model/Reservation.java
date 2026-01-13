package br.com.Portifolio.Room.Reservation.model;

import br.com.Portifolio.Room.Reservation.dto.ReservationDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Period period; //Time duration of the reservation
    private Integer reservationNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "start_date")
    private LocalDate start;//check in

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "end_date")
    private LocalDate end;//checkout

    @OneToOne(fetch = FetchType.EAGER)
    private User user;//Reference to the user

    @OneToOne(fetch = FetchType.EAGER)
    private Room room;//Reference to the room
    @Enumerated
    private ReservationStatus status;//the status of the reservation

    public Reservation(){}

    public Reservation(ReservationDto dto){//Use Data Trasnfer Object
        this.reservationNumber = dto.reservationNumber();
        this.period = dto.period();
        this.start = dto.start();
        this.end = dto.end();
        this.room = dto.room();
        this.status = dto.status();
    }

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public Integer getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(Integer reservationNumber) {
        reservationNumber = reservationNumber;
    }

}
