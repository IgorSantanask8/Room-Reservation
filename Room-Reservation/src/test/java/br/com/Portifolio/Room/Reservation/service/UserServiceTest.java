package br.com.Portifolio.Room.Reservation.service;

import br.com.Portifolio.Room.Reservation.dto.UserDto;
import br.com.Portifolio.Room.Reservation.exceptions.UserNotFoundException;
import br.com.Portifolio.Room.Reservation.model.User;
import br.com.Portifolio.Room.Reservation.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;
    @InjectMocks
    private UserService service;

    @Spy
    private User user;

    private UserDto dto;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @Test
    @DisplayName("Should create one User")
    void scenario01(){
        //ARRANGE
        this.dto = new UserDto("Example","12345678912",20,"Sao paulo");

        //ACT
        service.createUser(dto);

        then(repository).should().save(userArgumentCaptor.capture());
        User saveUser = userArgumentCaptor.getValue();

        //ASSERT
        Assertions.assertEquals(dto.name(),saveUser.getName());
        Assertions.assertEquals(dto.cpf(),saveUser.getCpf());
        Assertions.assertEquals(dto.age(),saveUser.getAge());
        Assertions.assertEquals(dto.cityOrigin(),saveUser.getCityOrigin());
    }

    @Test
    @DisplayName("Should delete User")
    void scenario03(){
        //ARRANGE
        this.dto = new UserDto("Example","12345678912",20,"Sao paulo");
        user = new User(dto);

        BDDMockito.given(repository.existsByCpf(user.getCpf())).willReturn(true);
        BDDMockito.given(repository.findByCpf(user.getCpf())).willReturn(user);

        //ACT
        service.deleteUser(user.getCpf());

        //ASSERT
        then(repository).should().delete(userArgumentCaptor.capture());
    }

    @Test
    @DisplayName("Should find User")
    void scenario04(){
        //ARRANGE
        this.dto = new UserDto("Example","12345678912",20,"Sao paulo");
        user = new User(dto);

        BDDMockito.given(repository.existsByCpf(user.getCpf())).willReturn(true);

        //ACT + ASSERT
        Assertions.assertDoesNotThrow(() -> service.searchUser(user.getCpf()));
    }

    @Test
    @DisplayName("Should Not find User")
    void scenario05(){
        //ARRANGE
        this.dto = new UserDto("Example","12345678912",20,"Sao paulo");
        user = new User(dto);

        BDDMockito.given(repository.existsByCpf(user.getCpf())).willReturn(false);

        //ACT + ASSERT
        Assertions.assertThrows(UserNotFoundException.class,() -> service.searchUser(user.getCpf()));
    }

}