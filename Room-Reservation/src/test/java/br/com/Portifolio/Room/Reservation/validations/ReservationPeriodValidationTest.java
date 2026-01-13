package br.com.Portifolio.Room.Reservation.validations;

import br.com.Portifolio.Room.Reservation.exceptions.ReservationException;
import br.com.Portifolio.Room.Reservation.model.Reservation;
import br.com.Portifolio.Room.Reservation.repository.RoomRespository;
import br.com.Portifolio.Room.Reservation.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ReservationPeriodValidationTest {


    private Reservation reservation = new Reservation();
    @InjectMocks
    private ReservationPeriodValidation validation;
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoomRespository roomRespository;

    @Test
    @DisplayName("Should throw ReservationException")
    void scenario01(){
        reservation.setEnd(LocalDate.now());
        reservation.setStart(reservation.getEnd().plusDays(1));


        ReservationException exception = Assertions.assertThrows(ReservationException.class,() -> validation.validar(reservation));
    }


}