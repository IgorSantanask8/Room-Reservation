package br.com.Portifolio.Room.Reservation.validations;

import br.com.Portifolio.Room.Reservation.exceptions.RoomException;
import br.com.Portifolio.Room.Reservation.model.Reservation;
import br.com.Portifolio.Room.Reservation.model.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RoomCapacityValidationTest {

    @InjectMocks
    private RoomCapacityValidation validation;
    @Mock
    private Room room;

    @Test
    @DisplayName("Should return Exception when The capacity or the room is minor than zero")
    void scenario01(){
        Reservation reservation = new Reservation();

        BDDMockito.when(room.getCapacity()).thenReturn(-2);
        reservation.setRoom(room);


        Assertions.assertThrows(RoomException.class,() -> {
            validation.validar(reservation);
        });
    }

}