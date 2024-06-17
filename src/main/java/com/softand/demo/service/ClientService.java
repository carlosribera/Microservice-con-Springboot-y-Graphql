package com.softand.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.softand.demo.common.exception.ClientNotFoundException;
import com.softand.demo.models.Client;
import com.softand.demo.repositories.ClientRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public Client getClientById(String id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found with ID: " + id));
        log.info("Found client {} by ID {}", client.getId(), id);
        return client;
    }

    public List<Client> getAllClients() {
        List<Client> clientList = clientRepository.findAll();
        if (CollectionUtils.isEmpty(clientList)) {
            log.info("No clients found");
            return null;
        }
        return clientList;
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Client updateClient(String id, Client clientEntity) {
        Client client = getClientById(id);

        client.setName(clientEntity.getName());
        client.setNit(clientEntity.getNit());
        client.setPhone(clientEntity.getPhone());

        return clientRepository.save(client);
    }

    public String deleteById(String id) {
        Client client = getClientById(id);
        this.clientRepository.deleteById(client.getId());
        log.info("Client by ID {} deleted", id);
        return "Client by ID: " + id + " deleted";
    }
}
