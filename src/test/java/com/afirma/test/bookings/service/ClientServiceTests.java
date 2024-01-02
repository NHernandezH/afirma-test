package com.afirma.test.bookings.service;

import com.afirma.test.bookings.common.dto.ClientDTO;
import com.afirma.test.bookings.common.exception.BookingNotFoundException;
import com.afirma.test.bookings.common.exception.ClientNotFoundException;
import com.afirma.test.bookings.repository.ClientRepository;
import com.afirma.test.bookings.repository.entity.Booking;
import com.afirma.test.bookings.repository.entity.Client;
import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.AssertionErrors;

import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
public class ClientServiceTests {

    @Autowired
    private ClientService clientService;

    @MockBean
    ClientRepository clientRepository;

    @Test
    public void whenSave_thenSave(){
        ClientDTO client = Instancio.create(ClientDTO.class);

        clientService.save(client);
        verify(clientRepository,times(1)).save(any());
    }

    @Test
    public void whenUpdate_thenThrowClientNotFoundException(){
        ClientDTO client = Instancio.create(ClientDTO.class);
        Long id = 1L;

        Assertions.assertThrows(ClientNotFoundException.class, () -> {
            clientService.update(client,id);
        });

    }

    @Test
    public void whenUpdate_thenUpdate(){
        ClientDTO client = Instancio.create(ClientDTO.class);
        Long id = 1L;
        Client clientEntity = Instancio.create(Client.class);
        when(clientRepository.findById(id)).thenReturn(Optional.of(clientEntity));
        clientService.update(client,id);
        verify(clientRepository,times(1)).save(clientEntity);
    }

    @Test
    public void whenDelete_thenThrowClientNotFoundException(){
        Long id = 1L;

        Assertions.assertThrows(ClientNotFoundException.class, () -> {
            clientService.delete(id);
        });

    }

    @Test
    public void whenDelete_thenDelete(){
        ClientDTO client = Instancio.create(ClientDTO.class);
        Long id = 1L;
        Client clientEntity = Instancio.create(Client.class);
        when(clientRepository.findById(id)).thenReturn(Optional.of(clientEntity));
        clientService.delete(id);
        verify(clientRepository,times(1)).delete(clientEntity);
    }

    @Test
    public void whenFindAll_thenFind(){
        clientService.findAll();
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    public void whenFindById_thenThrowClientNotFoundException(){
        ClientDTO client = Instancio.create(ClientDTO.class);
        Long id = 1L;

        Assertions.assertThrows(ClientNotFoundException.class, () -> {
            clientService.update(client,id);
        });

    }

    @Test
    public void whenFindById_thenFind(){
        Long id = 1L;
        Client clientEntity = Instancio.create(Client.class);
        when(clientRepository.findById(id)).thenReturn(Optional.of(clientEntity));
        ClientDTO client = clientService.findById(id);
        verify(clientRepository,times(1)).findById(id);
        Assertions.assertNotNull(client);
    }

}
