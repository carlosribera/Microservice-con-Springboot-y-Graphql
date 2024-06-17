package com.softand.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import com.softand.demo.models.Client;
import com.softand.demo.models.ClientInput;
import com.softand.demo.service.ClientService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@PreAuthorize("denyAll()")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @QueryMapping
    @PreAuthorize("hasAuthority('READ')")
    public Client getClientById(@Argument String id) {
        log.info("Query client in GraphQL Server by id {}", id);
        return clientService.getClientById(id);
    }

    @QueryMapping
    @PreAuthorize("permitAll()")
    public List<Client> getAllClients() {
        return this.clientService.getAllClients();
    }

    @MutationMapping
    @PreAuthorize("permitAll()")
    public Client createClient(@Argument ClientInput clientInput) {
        Client client = new Client();
        client.setName(clientInput.getName());
        client.setNit(clientInput.getNit());
        client.setPhone(clientInput.getPhone());

        return this.clientService.createClient(client);
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Client updateClient(@Argument String id, @Argument ClientInput clientInput) {
        Client client = new Client();
        client.setName(clientInput.getName());
        client.setNit(clientInput.getNit());
        client.setPhone(clientInput.getPhone());

        return this.clientService.updateClient(id, client);
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteClientById(@Argument String id) {
        this.clientService.deleteById(id);
        return "Client with id: " + id + " deleted successfully.";
    }
}
