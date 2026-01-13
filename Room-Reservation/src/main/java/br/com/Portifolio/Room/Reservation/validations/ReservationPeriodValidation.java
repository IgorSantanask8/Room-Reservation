package br.com.Portifolio.Room.Reservation.validations;

import br.com.Portifolio.Room.Reservation.exceptions.ReservationException;
import br.com.Portifolio.Room.Reservation.model.Reservation;
import br.com.Portifolio.Room.Reservation.repository.RoomRespository;
import br.com.Portifolio.Room.Reservation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservationPeriodValidation {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoomRespository roomRespository;

    public void validar(Reservation reservation){

        if(reservation.getStart()!= null && reservation.getEnd()!= null) {
            if (reservation.getStart().isAfter(reservation.getEnd())) {
                throw new ReservationException("The start can't be before the end");
            }
        }
        System.out.println("Check!");
    }

}
