package com.javathinked.example.demo_spring.mapper;

import com.javathinked.example.demo_spring.dto.ClientDto;
import com.javathinked.example.demo_spring.model.Client;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class ClientMapperTest {

    @Test
    void toEntity_mapsAllFields() {
        ClientDto dto = new ClientDto();
        dto.setId(42L);
        dto.setUsername("john.doe");
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setPostalCode("75001");
        dto.setCity("Paris");
        dto.setCompanyName("Acme");
        dto.setProfile("{\"level\":1}");

        Client e = ClientMapper.toEntity(dto);

        assertNotNull(e);
        assertEquals(42L, e.getId());
        assertEquals("john.doe", e.getUsername());
        assertEquals("John", e.getFirstName());
        assertEquals("Doe", e.getLastName());
        assertEquals("75001", e.getPostalCode());
        assertEquals("Paris", e.getCity());
        assertEquals("Acme", e.getCompanyName());
        assertEquals("{\"level\":1}", e.getProfile());
        // createdAt est géré par @PrePersist dans l'entité => rien à vérifier côté mapper
    }

    @Test
    void toEntity_nullDto_returnsNull() {
        assertNull(ClientMapper.toEntity(null));
    }

    @Test
    void toDto_mapsAllFields_andFormatsCreatedAt() {
        Client e = new Client();
        e.setId(7L);
        e.setUsername("alice");
        e.setFirstName("Alice");
        e.setLastName("Liddell");
        e.setPostalCode("69000");
        e.setCity("Lyon");
        e.setCompanyName("Wonderland");
        e.setProfile("{\"role\":\"ADMIN\"}");

        LocalDateTime now = LocalDateTime.of(2025, 8, 15, 10, 30, 5);
        e.setCreatedAt(now); // simulons la valeur BD

        ClientDto dto = ClientMapper.toDto(e);

        assertNotNull(dto);
        assertEquals(7L, dto.getId());
        assertEquals("alice", dto.getUsername());
        assertEquals("Alice", dto.getFirstName());
        assertEquals("Liddell", dto.getLastName());
        assertEquals("69000", dto.getPostalCode());
        assertEquals("Lyon", dto.getCity());
        assertEquals("Wonderland", dto.getCompanyName());
        assertEquals("{\"role\":\"ADMIN\"}", dto.getProfile());

        // Vérifie le format ISO_LOCAL_DATE_TIME
        String expected = now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        assertEquals(expected, dto.getCreatedAt());
    }

    @Test
    void toDto_createdAtNull_leavesDtoNull() {
        Client e = new Client();
        e.setUsername("bob");
        e.setCreatedAt(null);

        ClientDto dto = ClientMapper.toDto(e);

        assertNotNull(dto);
        assertNull(dto.getCreatedAt());
    }

    @Test
    void roundTrip_dtoToEntityToDto_preservesValuesExceptCreatedAt() {
        ClientDto input = new ClientDto();
        input.setId(1L);
        input.setUsername("round.user");
        input.setFirstName("Round");
        input.setLastName("Trip");
        input.setPostalCode("31000");
        input.setCity("Toulouse");
        input.setCompanyName("RT Corp");
        input.setProfile("{\"ok\":true}");

        Client e = ClientMapper.toEntity(input);
        // simulons que la BD a posé createdAt
        e.setCreatedAt(LocalDateTime.of(2025, 1, 1, 12, 0));

        ClientDto back = ClientMapper.toDto(e);

        assertEquals(input.getUsername(), back.getUsername());
        assertEquals(input.getFirstName(), back.getFirstName());
        assertEquals(input.getLastName(), back.getLastName());
        assertEquals(input.getPostalCode(), back.getPostalCode());
        assertEquals(input.getCity(), back.getCity());
        assertEquals(input.getCompanyName(), back.getCompanyName());
        assertEquals(input.getProfile(), back.getProfile());
        assertNotNull(back.getCreatedAt()); // formaté par le mapper
    }
}
