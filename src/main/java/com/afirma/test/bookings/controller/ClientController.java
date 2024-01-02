package com.afirma.test.bookings.controller;

import com.afirma.test.bookings.common.dto.ClientDTO;
import com.afirma.test.bookings.common.dto.CreatedClientDTO;
import com.afirma.test.bookings.common.dto.CreatedClientDetailDTO;
import com.afirma.test.bookings.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clients")
@Log4j2
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<CreatedClientDTO> save(@Valid @RequestBody ClientDTO client){
        CreatedClientDTO createdClient = clientService.save(client);
        return ResponseEntity
                .created(URI.create(String.format("/clients/%s",createdClient.getId())))
                .body(createdClient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreatedClientDTO> update(@Valid @RequestBody ClientDTO client, @PathVariable Long id){
        CreatedClientDTO createdClient = clientService.update(client, id);
        return ResponseEntity.ok(createdClient);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        clientService.delete(id);
    }

    @GetMapping
    public ResponseEntity<List<CreatedClientDTO>> findAll(){
        List<CreatedClientDTO> clients = clientService.findAll();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreatedClientDetailDTO> findById( @PathVariable Long id){
        CreatedClientDetailDTO client = clientService.findById(id);
        return ResponseEntity.ok(client);
    }

}
