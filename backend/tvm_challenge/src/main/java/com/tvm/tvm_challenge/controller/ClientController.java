package com.tvm.tvm_challenge.controller;

import com.tvm.tvm_challenge.entity.Clients;
import com.tvm.tvm_challenge.services.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/client")
public class ClientController {

    private ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping
    public List<Clients> findAllClients(){
        List<Clients> clients = service.findAllClients();
        return clients;
    }
    @GetMapping("/{id}")
    public Optional<Clients> findClientByd(@PathVariable Long id){
        Optional<Clients> client = service.findClientById(id);
        return client;
    }

    @PostMapping
    public void addClient(@RequestBody Clients clients){
        service.addClient(clients);
    }

    @PutMapping
    public void updateClient(@RequestBody Clients clients){
        service.updateClient(clients);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id){
        service.deleteClient(id);
    }

}
