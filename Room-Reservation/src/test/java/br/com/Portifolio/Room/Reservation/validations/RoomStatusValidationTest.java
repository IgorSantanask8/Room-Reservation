package br.com.Portifolio.Room.Reservation.validations;

import br.com.Portifolio.Room.Reservation.exceptions.RoomException;
import br.com.Portifolio.Room.Reservation.model.Reservation;
import br.com.Portifolio.Room.Reservation.model.Room;
import br.com.Portifolio.Room.Reservation.model.RoomStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RoomStatusValidationTest {

    @InjectMocks
    private RoomStatusValidation validation;
    @Mock
    private Room room;


    @Test
    @DisplayName("Should throw Exception when call method validation")
    void scenario01(){
        Reservation reservation = new Reservation();
        reservation.setRoom(room);

        BDDMockito.given(room.getStatus()).willReturn(RoomStatus.INACTIVE);

        Assertions.assertThrows(RoomException.class,() -> validation.validar(reservation));
    }
    @Test
    @DisplayName("Should not throw")
    void scenario02(){
        Reservation reservation = new Reservation();
        reservation.setRoom(room);

        BDDMockito.given(room.getStatus()).willReturn(RoomStatus.AVAILABLE);

        Assertions.assertDoesNotThrow(() -> validation.validar(reservation));
    }
    @Test
    @DisplayName("Should throw Exception when the room is inactive")
    void scenario03(){
        Reservation reservation = new Reservation();
        reservation.setRoom(room);

        BDDMockito.given(room.getStatus()).willReturn(RoomStatus.INACTIVE);

        Assertions.assertThrows(RoomException.class,() -> validation.validar(reservation));
    }

}