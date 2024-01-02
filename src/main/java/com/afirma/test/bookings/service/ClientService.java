package com.afirma.test.bookings.service;

import com.afirma.test.bookings.common.dto.ClientDTO;
import com.afirma.test.bookings.common.dto.CreatedClientDTO;
import com.afirma.test.bookings.common.dto.CreatedClientDetailDTO;
import com.afirma.test.bookings.common.exception.ClientNotFoundException;
import com.afirma.test.bookings.mapper.ClientMapper;
import com.afirma.test.bookings.repository.ClientRepository;
import com.afirma.test.bookings.repository.entity.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    @Transactional
    public CreatedClientDTO save(ClientDTO clientDto){
        Client client = ClientMapper.INSTANCE.dtoToEntity(clientDto);
        clientRepository.save(client);
        return ClientMapper.INSTANCE.entityToDto(client);
    }

    @Transactional
    public CreatedClientDTO update(ClientDTO clientDto, Long id){
        Client client = findEntityById(id);
        ClientMapper.INSTANCE.update(client,clientDto);
        clientRepository.save(client);
        return  ClientMapper.INSTANCE.entityToDto(client);
    }

    @Transactional
    public void delete(Long id){
        Client client = findEntityById(id);
        clientRepository.delete(client);
    }


    public List<CreatedClientDTO> findAll(){
        List<Client> clients = clientRepository.findAll();
        return ClientMapper.INSTANCE.entityToDto(clients);
    }

    public CreatedClientDetailDTO findById(Long id){
        Client client = findEntityById(id);
        return ClientMapper.INSTANCE.entityToDetailDto(client);
    }

    public Client findEntityById(Long id){
        return clientRepository.findById(id)
                .orElseThrow(()->ClientNotFoundException.of(id));

    }

}
