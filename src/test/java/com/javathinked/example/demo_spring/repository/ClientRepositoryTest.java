package com.javathinked.example.demo_spring.repository;

import com.javathinked.example.demo_spring.model.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) // force H2 embarquée
class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    private Client newClient(String username) {
        Client c = new Client();
        c.setUsername(username);
        c.setFirstName("Alice");
        c.setLastName("Liddell");
        c.setPostalCode("69000");
        c.setCity("Lyon");
        c.setCompanyName("Wonderland");
        c.setProfile("{\"role\":\"USER\"}");
        return c;
    }

    @Test
    void save_and_findById_shouldWork() {
        Client saved = clientRepository.save(newClient("alice"));
        assertNotNull(saved.getId(), "L'ID doit être généré");
        assertNotNull(saved.getCreatedAt(), "createdAt doit être renseigné par @PrePersist");

        Optional<Client> found = clientRepository.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals("alice", found.get().getUsername());
    }

    @Test
    void findAll_shouldReturnMultiple() {
        clientRepository.save(newClient("user1"));
        clientRepository.save(newClient("user2"));

        List<Client> all = clientRepository.findAll();
        assertTrue(all.size() >= 2);
    }
}
