package com.tvm.tvm_challenge.services;

import com.tvm.tvm_challenge.entity.Clients;
import com.tvm.tvm_challenge.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public List<Clients> findAllClients() {
        return repository.findAll();
    }

    public Optional<Clients> findClientById(Long id){
            Optional<Clients> client = repository.findById(id);
        return client;
    }

    public Clients addClient (Clients clients){
        return repository.save(clients);
    }

    public void updateClient (Clients clients){
        System.out.println(clients);
        if(clients.getId() > 0)
            System.out.println(clients);
            repository.save(clients);
    }

    public void deleteClient (Long id){
        repository.deleteById(id);
    }
}
