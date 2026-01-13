package br.com.Portifolio.Room.Reservation.controller;

import br.com.Portifolio.Room.Reservation.dto.RoomDto;
import br.com.Portifolio.Room.Reservation.model.Room;
import br.com.Portifolio.Room.Reservation.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel/v1")
public class RoomController {

    @Autowired
    private RoomService service;

    @PostMapping("/create/room")
    ResponseEntity<Room> createRoom(@RequestBody RoomDto dto){
        service.createRoom(dto);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/search/room/{number}")
    Room searchRoom(@PathVariable Integer number){
         return service.searchRoom(number);
    }
    @GetMapping("/search/room")
    List<RoomDto> searchAllRooms(){
        return service.searchAllRooms();
    }
    @DeleteMapping("/delete/room/{number}")
    ResponseEntity<Room> deleteRoom(@PathVariable Integer number){
        service.deleteRoom(number);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/change/room/{number}/status")
    public ResponseEntity<Room> changeStatusInactive(@PathVariable Integer number){
        service.changeStatusInactive(number);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/change/room/{number}/status/available")
    public ResponseEntity<Room> changeStatusAvailable(@PathVariable Integer number){
        service.changeStatusAvailable(number);
        return ResponseEntity.ok().build();
    }
}
