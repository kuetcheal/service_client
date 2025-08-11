package com.javathinked.example.demo_spring.controller;

import com.javathinked.example.demo_spring.dto.ClientDto;
import com.javathinked.example.demo_spring.mapper.ClientMapper;
import com.javathinked.example.demo_spring.model.Client;
import com.javathinked.example.demo_spring.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/clients")
// @CrossOrigin(origins = "http://localhost:3000") // utile quand tu brancheras Nuxt
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<ClientDto> getAll() {
        return clientService.getAllClients().stream()
                .map(ClientMapper::toDto)
                .collect(toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getById(@PathVariable Long id) {
        return clientService.getClientById(id)
                .map(ClientMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ClientDto> create(@Valid @RequestBody ClientDto dto) {
        Client toSave = ClientMapper.toEntity(dto);
        Client saved = clientService.createClient(toSave);
        ClientDto out = ClientMapper.toDto(saved);
        return ResponseEntity.created(URI.create("/api/clients/" + saved.getId()))
                .body(out);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> update(@PathVariable Long id, @Valid @RequestBody ClientDto dto) {
        Client updated = clientService.updateClient(id, ClientMapper.toEntity(dto));
        return ResponseEntity.ok(ClientMapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
