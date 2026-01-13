package br.com.Portifolio.Room.Reservation.controller;

import br.com.Portifolio.Room.Reservation.dto.UserDto;
import br.com.Portifolio.Room.Reservation.model.User;
import br.com.Portifolio.Room.Reservation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/create/user")
    ResponseEntity<User> createUser(@RequestBody UserDto dto){
        service.createUser(dto);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/search/user/{cpf}")
    User searchUser(@PathVariable String cpf){
        return service.searchUser(cpf);
    }
    @GetMapping("/search/users")
    List<UserDto> searchAllUsers(){
        return service.searchAllUsers();
    }
    @DeleteMapping("/delete/user/{cpf}")
    ResponseEntity<User> deleteUser(@PathVariable String cpf){
        service.deleteUser(cpf);
        return ResponseEntity.noContent().build();
    }
}
