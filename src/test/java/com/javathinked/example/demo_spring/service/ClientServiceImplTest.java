package com.javathinked.example.demo_spring.service;

import com.javathinked.example.demo_spring.model.Client;
import com.javathinked.example.demo_spring.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientServiceImplTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnAllClients() {
        Client client1 = new Client(
            "alice",         // username
            "Alice",         // firstName
            "Doe",           // lastName
            "75001",         // postalCode
            "Paris",         // city
            "Acme",          // companyName
            "Client test"    // profile
        );
        client1.setId(1L);

        Client client2 = new Client(
            "bob",
            "Bob",
            "Smith",
            "69000",
            "Lyon",
            "Globex",
            "Client prod"
        );
        client2.setId(2L);

        when(clientRepository.findAll()).thenReturn(Arrays.asList(client1, client2));

        List<Client> result = clientService.getAllClients();

        assertEquals(2, result.size());
        assertEquals("Alice", result.get(0).getFirstName());
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnClientById() {
        Client client = new Client(
            "alice", "Alice", "Doe", "75001", "Paris", "Acme", "Client test"
        );
        client.setId(1L);

        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        Optional<Client> result = clientService.getClientById(1L);

        assertTrue(result.isPresent());
        assertEquals("Alice", result.get().getFirstName());
        verify(clientRepository, times(1)).findById(1L);
    }

    @Test
    void shouldReturnEmptyWhenClientNotFound() {
        when(clientRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<Client> result = clientService.getClientById(999L);

        assertFalse(result.isPresent());
        verify(clientRepository, times(1)).findById(999L);
    }

    @Test
    void shouldCreateClient() {
        Client client = new Client(
            "alice", "Alice", "Doe", "75001", "Paris", "Acme", "Client test"
        );
        Client savedClient = new Client(
            "alice", "Alice", "Doe", "75001", "Paris", "Acme", "Client test"
        );
        savedClient.setId(1L);

        when(clientRepository.save(client)).thenReturn(savedClient);

        Client result = clientService.createClient(client);

        assertEquals(1L, result.getId());
        assertEquals("Alice", result.getFirstName());
        verify(clientRepository, times(1)).save(client);
    }

    @Test
    void shouldUpdateClient() {
        Client existingClient = new Client(
            "alice", "Alice", "Doe", "75001", "Paris", "Acme", "Client test"
        );
        existingClient.setId(1L);

        Client updatedClient = new Client(
            "alice", "Alice Updated", "Doe Updated", "75002", "Lyon", "New Company", "Updated profile"
        );

        when(clientRepository.findById(1L)).thenReturn(Optional.of(existingClient));
        when(clientRepository.save(existingClient)).thenReturn(existingClient);

        Client result = clientService.updateClient(1L, updatedClient);

        assertEquals("Alice Updated", result.getFirstName());
        assertEquals("Doe Updated", result.getLastName());
        assertEquals("75002", result.getPostalCode());
        assertEquals("Lyon", result.getCity());
        assertEquals("New Company", result.getCompanyName());
        assertEquals("Updated profile", result.getProfile());
        verify(clientRepository, times(1)).findById(1L);
        verify(clientRepository, times(1)).save(existingClient);
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonExistentClient() {
        Client updatedClient = new Client(
            "alice", "Alice", "Doe", "75001", "Paris", "Acme", "Client test"
        );

        when(clientRepository.findById(999L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> clientService.updateClient(999L, updatedClient)
        );

        assertEquals("Client not found: 999", exception.getMessage());
        verify(clientRepository, times(1)).findById(999L);
        verify(clientRepository, never()).save(any());
    }

    @Test
    void shouldDeleteClient() {
        clientService.deleteClient(1L);

        verify(clientRepository, times(1)).deleteById(1L);
    }
}
