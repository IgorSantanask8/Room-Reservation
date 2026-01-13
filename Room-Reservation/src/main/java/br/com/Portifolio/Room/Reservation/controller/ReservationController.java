package br.com.Portifolio.Room.Reservation.controller;

import br.com.Portifolio.Room.Reservation.dto.ReservationDto;
import br.com.Portifolio.Room.Reservation.model.Reservation;
import br.com.Portifolio.Room.Reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/hotel/v1")
public class ReservationController {

    @Autowired
    private ReservationService service;

    @PostMapping("/create/reservation/{cpf}/{number}")
    ResponseEntity<Reservation> createReservation(@PathVariable String cpf,
                                                  @PathVariable Integer number,
                                                  @RequestBody ReservationDto dto) throws IOException {
        service.createReservation(cpf, number, dto);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/search/reservation")
    List<ReservationDto> searchReservations(){
        return service.searchReservations();
    }
    @GetMapping("/search/reservation/{reservationNumber}")
    Reservation searchReservationByNumber(@PathVariable Integer reservationNumber){
        return service.searchReservationByNumber(reservationNumber);
    }
    @DeleteMapping("/cancel/reservation/{reservationNumber}")
    public ResponseEntity<Reservation> cancelReservation(@PathVariable Integer reservationNumber){
        service.cancelReservation(reservationNumber);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/change/reservation/{reservationNumber}/room/{number}/{number2}")
    public ResponseEntity<Reservation> changeRoom(@PathVariable Integer reservationNumber, @PathVariable Integer number,
                                                  @PathVariable Integer number2){
        service.changeRoom(reservationNumber, number, number2);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/search/reservation/room/{number}")
    public Reservation searchReservationByRoom(@PathVariable Integer number){
        return service.searchReservationByRoom(number);
    }
}
