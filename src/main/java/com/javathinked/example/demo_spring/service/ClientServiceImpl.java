package com.javathinked.example.demo_spring.service;

import com.javathinked.example.demo_spring.model.Client;
import com.javathinked.example.demo_spring.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(Long id, Client client) {
        return clientRepository.findById(id)
                .map(existing -> {
                    existing.setUsername(client.getUsername());
                    existing.setFirstName(client.getFirstName());
                    existing.setLastName(client.getLastName());
                    existing.setPostalCode(client.getPostalCode());
                    existing.setCity(client.getCity());
                    existing.setCompanyName(client.getCompanyName());
                    existing.setProfile(client.getProfile());
                    return clientRepository.save(existing);
                })
                .orElseThrow(() -> new IllegalArgumentException("Client not found: " + id));
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
