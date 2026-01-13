package br.com.Portifolio.Room.Reservation.validations;

import br.com.Portifolio.Room.Reservation.exceptions.RoomException;
import br.com.Portifolio.Room.Reservation.model.Reservation;
import br.com.Portifolio.Room.Reservation.model.RoomStatus;
import org.springframework.stereotype.Component;

@Component
public class RoomStatusValidation implements ValidadorReserva {

    @Override
    public void validar(Reservation reservation){
        var room = reservation.getRoom();

        if(room.getStatus() == RoomStatus.INACTIVE){
            throw new RoomException("This room is inactive");
        }else if(room.getStatus() == RoomStatus.OCCUPIED){
            throw new RoomException( "This room is occupied");
        }else if(room.getStatus() == RoomStatus.AVAILABLE){
            System.out.println("This room is available");
        }
        System.out.println("The room is active!");
    }


}
