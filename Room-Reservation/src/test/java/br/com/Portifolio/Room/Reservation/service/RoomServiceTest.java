package br.com.Portifolio.Room.Reservation.service;

import br.com.Portifolio.Room.Reservation.dto.RoomDto;
import br.com.Portifolio.Room.Reservation.exceptions.RoomNotFoundException;
import br.com.Portifolio.Room.Reservation.model.Room;
import br.com.Portifolio.Room.Reservation.model.RoomStatus;
import br.com.Portifolio.Room.Reservation.model.RoomType;
import br.com.Portifolio.Room.Reservation.repository.RoomRespository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class RoomServiceTest {

    @Mock
    private RoomRespository roomRespository;
    @InjectMocks
    private RoomService service;

    @Captor
    private ArgumentCaptor<Room> roomCapture;

    @Spy
    private Room room_1;

    private RoomDto dto;

    @Test
    @DisplayName("Should save one Room")
    void scenario01(){
        //ARRANGE
        this.dto = new RoomDto(100,3, RoomType.BASIC,new BigDecimal("30.10"),RoomStatus.AVAILABLE);

        //ACT
        service.createRoom(dto);

        //ARRANGE
        then(roomRespository).should().save(roomCapture.capture());
        Room roomSave = roomCapture.getValue();
        Assertions.assertEquals(dto.number(),roomSave.getNumber());
        Assertions.assertEquals(dto.capacity(),roomSave.getCapacity());
        Assertions.assertEquals(dto.type(),roomSave.getType());
        Assertions.assertEquals(dto.dailyPrice(),roomSave.getDailyPrice());
        Assertions.assertEquals(dto.status(),roomSave.getStatus());
    }

    @Test
    @DisplayName("Should delete Room")
    void scenario02(){
        //ARRANGE
        this.dto = new RoomDto(100,3, RoomType.BASIC,new BigDecimal("30.10"),RoomStatus.AVAILABLE);
        Room room = new Room(dto);

        //ACT
        service.deleteRoom(room.getNumber());

        //ASSERT
        then(roomRespository).should().delete(roomCapture.capture());
    }

    @Test
    @DisplayName("Should find Room")
    void scenario03(){
        //ARRANGE
        this.dto = new RoomDto(100,3, RoomType.BASIC,new BigDecimal("30.10"),RoomStatus.AVAILABLE);
        room_1 = new Room(dto);

        BDDMockito.given(roomRespository.existsByNumber(100)).willReturn(true);

        //ACT
        //ASSERT
        Assertions.assertDoesNotThrow(()-> service.searchRoom(room_1.getNumber()));
        System.out.println(room_1);
    }

    @Test
    @DisplayName("Should not find Room")
    void scenario04(){
        //ARRANGE
        this.dto = new RoomDto(100,3, RoomType.BASIC,new BigDecimal("30.10"),RoomStatus.AVAILABLE);
        room_1 = new Room(dto);

        BDDMockito.given(!roomRespository.existsByNumber(100)).willThrow(RoomNotFoundException.class);

        //ACT
        //ASSERT
        Assertions.assertThrows(RoomNotFoundException.class,() -> service.searchRoom(room_1.getNumber()));
    }

    @Test
    @DisplayName("Should change Status to Inactive")
    void scenario05(){
        //ARRANGE
        this.dto = new RoomDto(100,3, RoomType.BASIC,new BigDecimal("30.10"),RoomStatus.AVAILABLE);
        room_1 = new Room(dto);

        BDDMockito.given(roomRespository.existsByNumber(100)).willReturn(true);
        BDDMockito.given(roomRespository.findByNumber(100)).willReturn(room_1);

        service.changeStatusInactive(room_1.getNumber());

        then(roomRespository).should().save(roomCapture.capture());
        Room roomSave = roomCapture.getValue();

        Assertions.assertEquals(room_1.getStatus(),roomSave.getStatus());
        System.out.println(room_1.getStatus());
    }

    @Test
    @DisplayName("Should change Status to Available")
    void scenario06(){
        //ARRANGE
        this.dto = new RoomDto(100,3, RoomType.BASIC,new BigDecimal("30.10"),RoomStatus.INACTIVE);
        room_1 = new Room(dto);

        BDDMockito.given(roomRespository.existsByNumber(100)).willReturn(true);
        BDDMockito.given(roomRespository.findByNumber(100)).willReturn(room_1);

        service.changeStatusAvailable(room_1.getNumber());

        then(roomRespository).should().save(roomCapture.capture());
        Room roomSave = roomCapture.getValue();

        Assertions.assertEquals(room_1.getStatus(),roomSave.getStatus());
        System.out.println(room_1.getStatus());
    }

}