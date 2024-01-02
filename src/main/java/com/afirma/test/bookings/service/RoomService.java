package com.afirma.test.bookings.service;

import com.afirma.test.bookings.common.dto.CreatedRoomDTO;
import com.afirma.test.bookings.common.dto.RoomDTO;
import com.afirma.test.bookings.common.exception.DatesNotAvailableException;
import com.afirma.test.bookings.common.exception.RoomNotFoundException;
import com.afirma.test.bookings.common.exception.RoomNumberExistsException;
import com.afirma.test.bookings.mapper.RoomMapper;
import com.afirma.test.bookings.repository.RoomRepository;
import com.afirma.test.bookings.repository.entity.Room;
import com.afirma.test.bookings.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    public Room validateAvailability(Long id, String startDate, String endDate){
        Room room = findEntityById(id);
        room.getBookings().stream()
                .forEach(booking->{
                    Boolean rangesOverlap = DateUtils.rangesOverlap(booking.getStartDate(),booking.getEndDate(),
                            DateUtils.stringToLocalDate(startDate),DateUtils.stringToLocalDate(endDate));
                    if(rangesOverlap){
                        throw DatesNotAvailableException.of(startDate,endDate,id);
                    }
                });
        return room;
    }

    public BigDecimal calculateTotalAmount(Integer totalNights, Room room){
        return room.getPricePerNight().multiply(BigDecimal.valueOf(totalNights));
    }

    @Transactional
    public CreatedRoomDTO save(RoomDTO roomDTO){
        validateIfRowNumberExists(roomDTO.getNumber());
        Room room = RoomMapper.INSTANCE.dtoToEntity(roomDTO);
        room = roomRepository.save(room);
        return RoomMapper.INSTANCE.entityToCreatedDto(room);

    }


    public RoomDTO findById(Long id){
        Room room = findEntityById(id);
        return RoomMapper.INSTANCE.entityToDto(room);
    }

    public Room findEntityById(Long id){
        return roomRepository.findById(id)
                .orElseThrow(()-> RoomNotFoundException.of(id));
    }

    public List<RoomDTO> findAll(){
        List<Room> rooms = roomRepository.findAll();
        return RoomMapper.INSTANCE.entityToDto(rooms);
    }

    @Transactional
    public void delete(Long id){
        Room room = roomRepository.findById(id)
                .orElseThrow(()-> RoomNotFoundException.of(id));
        roomRepository.delete(room);
    }

    @Transactional
    public void update(RoomDTO roomDTO, Long id){
        Room room = roomRepository.findById(id)
                .orElseThrow(()->RoomNotFoundException.of(id));
        validateIfRowNumberExists(roomDTO.getNumber());
        RoomMapper.INSTANCE.update(room, roomDTO);
        roomRepository.save(room);
    }

    private void validateIfRowNumberExists(String roomNumber){
        Integer total = roomRepository.countByNumber(roomNumber.toUpperCase());
        if(total>0){
            throw RoomNumberExistsException.of(roomNumber);
        }
    }
}
