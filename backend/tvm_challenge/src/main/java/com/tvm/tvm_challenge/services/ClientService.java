package com.tvm.tvm_challenge.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvm.tvm_challenge.entity.Clients;
import com.tvm.tvm_challenge.repository.ClientRepository;

@Service
public class ClientService {
    @Autowired
    ClientRepository repository;

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
        if(clients.getId() > 0)
            repository.save(clients);
    }

    public void deleteClient (Long id){
        repository.deleteById(id);
    }
}
