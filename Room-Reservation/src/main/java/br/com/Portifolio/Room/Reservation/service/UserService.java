package br.com.Portifolio.Room.Reservation.service;

import br.com.Portifolio.Room.Reservation.dto.UserDto;
import br.com.Portifolio.Room.Reservation.exceptions.UserNotFoundException;
import br.com.Portifolio.Room.Reservation.model.User;
import br.com.Portifolio.Room.Reservation.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<UserDto> convert(List<User> users){
        return users.stream()
                .map(u -> new UserDto(u.getName(),u.getCpf(),u.getAge(),u.getCityOrigin()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void createUser(UserDto dto) {

        User user = new User(dto);

        repository.save(user);
    }

    public User searchUser(String cpf) {
        if(!repository.existsByCpf(cpf)){
            throw new UserNotFoundException("This user does not exist");
        }
        return repository.findByCpf(cpf);
    }

    public List<UserDto> searchAllUsers() {
        return convert(repository.findAll());
    }

    @Transactional
    public void deleteUser(String cpf) {
        if(!repository.existsByCpf(cpf)){
            throw new UserNotFoundException("This user does not exist");
        }
        User user = repository.findByCpf(cpf);
        repository.delete(user);
    }
}
