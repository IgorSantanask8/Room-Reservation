package br.com.Portifolio.Room.Reservation.validations;

import br.com.Portifolio.Room.Reservation.exceptions.RoomException;
import br.com.Portifolio.Room.Reservation.model.Reservation;
import br.com.Portifolio.Room.Reservation.model.Room;
import org.springframework.stereotype.Component;

@Component
public class RoomCapacityValidation implements ValidadorReserva{

    @Override
    public void validar(Reservation reservation){

        var room = reservation.getRoom();

        if(room.getCapacity() < 0){
            throw new RoomException("The capacity can't be minor than zero");
        }
        System.out.println("Check! ");
    }

}
