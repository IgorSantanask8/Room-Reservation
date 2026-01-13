package br.com.Portifolio.Room.Reservation.service;

import br.com.Portifolio.Room.Reservation.dto.RoomDto;
import br.com.Portifolio.Room.Reservation.exceptions.RoomNotFoundException;
import br.com.Portifolio.Room.Reservation.model.Room;
import br.com.Portifolio.Room.Reservation.model.RoomStatus;
import br.com.Portifolio.Room.Reservation.repository.RoomRespository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    @Autowired
    private RoomRespository roomRespository;

    public List<RoomDto> convert(List<Room> room){
        return room.stream()
                .map(r -> new RoomDto(r.getNumber(),r.getCapacity(),r.getType(),r.getDailyPrice(),
                        r.getStatus()))
                .collect(Collectors.toList());
    }

    @Transactional
    public Room createRoom(RoomDto dto) {

        Room room = new Room(dto);
        return roomRespository.save(room);
    }

    public Room searchRoom(Integer number) {
        if (!roomRespository.existsByNumber(number)) {
            throw new RoomNotFoundException("This room does not exist");
        }
        return roomRespository.findByNumber(number);
    }

    public List<RoomDto> searchAllRooms() {
        return convert(roomRespository.findAll());
    }

    @Transactional
    public void deleteRoom(Integer number) {
        Room room = roomRespository.findByNumber(number);

        roomRespository.delete(room);
    }

    public void changeStatusInactive(Integer number) {
        if(!roomRespository.existsByNumber(number)){
            throw new RoomNotFoundException("This room does not exist");
        }

        Room room = roomRespository.findByNumber(number);
        room.setStatus(RoomStatus.INACTIVE);

        roomRespository.save(room);
    }

    public void changeStatusAvailable(Integer number) {
        if(!roomRespository.existsByNumber(number)){
            throw new RoomNotFoundException("This room does not exist");
        }

        Room room = roomRespository.findByNumber(number);
        room.setStatus(RoomStatus.AVAILABLE);

        roomRespository.save(room);
    }
}
