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
        client1.setId(1L); // important !

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
        assertEquals("Alice", result.get(0).getFirstName()); // CORRECTION ICI
        verify(clientRepository, times(1)).findAll();
    }
}
