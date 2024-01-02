package com.afirma.test.bookings.controller;

import com.afirma.test.bookings.common.dto.CreatedRoomDTO;
import com.afirma.test.bookings.common.dto.RoomDTO;
import com.afirma.test.bookings.repository.entity.Room;
import com.afirma.test.bookings.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rooms")
@Log4j2
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<CreatedRoomDTO> save(@Valid @RequestBody RoomDTO room){
        CreatedRoomDTO roomResponse = roomService.save(room);
        return ResponseEntity
                .created(URI.create(String.format("/rooms/%s",roomResponse.getId())))
                .body(roomResponse);
    }


    @PutMapping("/{id}")
    public ResponseEntity<RoomDTO> update(@RequestBody RoomDTO room, @PathVariable Long id){
        roomService.update(room, id);
        return ResponseEntity.ok(room);
    }

    @GetMapping("/{id}")
    public RoomDTO findById(@PathVariable Long id){
        return roomService.findById(id);
    }

    @GetMapping
    public List<RoomDTO> findAll(){
        return roomService.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        roomService.delete(id);
    }
}
