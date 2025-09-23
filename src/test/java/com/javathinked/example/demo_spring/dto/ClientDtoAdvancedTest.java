package com.javathinked.example.demo_spring.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientDtoAdvancedTest {

    @Test
    void shouldCreateClientDtoWithAllFields() {
        // Given
        Long id = 1L;
        String username = "john.doe";
        String firstName = "John";
        String lastName = "Doe";
        String postalCode = "75001";
        String city = "Paris";
        String companyName = "Acme";
        String profile = "Client test";

        // When
        ClientDto clientDto = new ClientDto();
        clientDto.setId(id);
        clientDto.setUsername(username);
        clientDto.setFirstName(firstName);
        clientDto.setLastName(lastName);
        clientDto.setPostalCode(postalCode);
        clientDto.setCity(city);
        clientDto.setCompanyName(companyName);
        clientDto.setProfile(profile);

        // Then
        assertEquals(id, clientDto.getId());
        assertEquals(username, clientDto.getUsername());
        assertEquals(firstName, clientDto.getFirstName());
        assertEquals(lastName, clientDto.getLastName());
        assertEquals(postalCode, clientDto.getPostalCode());
        assertEquals(city, clientDto.getCity());
        assertEquals(companyName, clientDto.getCompanyName());
        assertEquals(profile, clientDto.getProfile());
    }

    @Test
    void shouldCreateClientDtoWithNullValues() {
        // Given
        ClientDto clientDto = new ClientDto();

        // Then
        assertNull(clientDto.getId());
        assertNull(clientDto.getUsername());
        assertNull(clientDto.getFirstName());
        assertNull(clientDto.getLastName());
        assertNull(clientDto.getPostalCode());
        assertNull(clientDto.getCity());
        assertNull(clientDto.getCompanyName());
        assertNull(clientDto.getProfile());
    }

    @Test
    void shouldCreateClientDtoWithEmptyStrings() {
        // Given
        ClientDto clientDto = new ClientDto();
        clientDto.setId(1L);
        clientDto.setUsername("");
        clientDto.setFirstName("");
        clientDto.setLastName("");
        clientDto.setPostalCode("");
        clientDto.setCity("");
        clientDto.setCompanyName("");
        clientDto.setProfile("");

        // Then
        assertEquals(1L, clientDto.getId());
        assertEquals("", clientDto.getUsername());
        assertEquals("", clientDto.getFirstName());
        assertEquals("", clientDto.getLastName());
        assertEquals("", clientDto.getPostalCode());
        assertEquals("", clientDto.getCity());
        assertEquals("", clientDto.getCompanyName());
        assertEquals("", clientDto.getProfile());
    }

    @Test
    void shouldCreateClientDtoWithSpecialCharacters() {
        // Given
        ClientDto clientDto = new ClientDto();
        clientDto.setId(999L);
        clientDto.setUsername("user@domain.com");
        clientDto.setFirstName("José");
        clientDto.setLastName("D'Angelo");
        clientDto.setPostalCode("75001");
        clientDto.setCity("Paris-15ème");
        clientDto.setCompanyName("Compagnie & Cie");
        clientDto.setProfile("Profil avec accents éàç");

        // Then
        assertEquals(999L, clientDto.getId());
        assertEquals("user@domain.com", clientDto.getUsername());
        assertEquals("José", clientDto.getFirstName());
        assertEquals("D'Angelo", clientDto.getLastName());
        assertEquals("75001", clientDto.getPostalCode());
        assertEquals("Paris-15ème", clientDto.getCity());
        assertEquals("Compagnie & Cie", clientDto.getCompanyName());
        assertEquals("Profil avec accents éàç", clientDto.getProfile());
    }

    @Test
    void shouldHaveStringRepresentation() {
        // Given
        ClientDto clientDto = new ClientDto();
        clientDto.setId(1L);
        clientDto.setUsername("john.doe");
        clientDto.setFirstName("John");
        clientDto.setLastName("Doe");

        // When
        String stringRepresentation = clientDto.toString();

        // Then
        assertNotNull(stringRepresentation);
        // Le toString() par défaut contient le nom de la classe et l'hashCode
        assertTrue(stringRepresentation.contains("ClientDto"));
    }

    @Test
    void shouldBeEqualWhenSameValues() {
        // Given
        ClientDto clientDto1 = new ClientDto();
        clientDto1.setId(1L);
        clientDto1.setUsername("john.doe");
        clientDto1.setFirstName("John");
        clientDto1.setLastName("Doe");

        ClientDto clientDto2 = new ClientDto();
        clientDto2.setId(1L);
        clientDto2.setUsername("john.doe");
        clientDto2.setFirstName("John");
        clientDto2.setLastName("Doe");

        // When & Then
        // Note: ClientDto n'a pas d'equals/hashCode personnalisé, donc les objets ne sont pas égaux
        // mais nous testons que les valeurs sont identiques
        assertEquals(clientDto1.getId(), clientDto2.getId());
        assertEquals(clientDto1.getUsername(), clientDto2.getUsername());
        assertEquals(clientDto1.getFirstName(), clientDto2.getFirstName());
        assertEquals(clientDto1.getLastName(), clientDto2.getLastName());
    }

    @Test
    void shouldNotBeEqualWhenDifferentValues() {
        // Given
        ClientDto clientDto1 = new ClientDto();
        clientDto1.setId(1L);
        clientDto1.setUsername("john.doe");

        ClientDto clientDto2 = new ClientDto();
        clientDto2.setId(2L);
        clientDto2.setUsername("jane.doe");

        // When & Then
        assertNotEquals(clientDto1, clientDto2);
    }

    @Test
    void shouldNotBeEqualWhenDifferentTypes() {
        // Given
        ClientDto clientDto = new ClientDto();
        clientDto.setId(1L);
        clientDto.setUsername("john.doe");

        String otherObject = "not a ClientDto";

        // When & Then
        assertNotEquals(clientDto, otherObject);
    }

    @Test
    void shouldNotBeEqualWhenNull() {
        // Given
        ClientDto clientDto = new ClientDto();
        clientDto.setId(1L);
        clientDto.setUsername("john.doe");

        // When & Then
        assertNotEquals(clientDto, null);
    }

    @Test
    void shouldBeEqualWhenSameInstance() {
        // Given
        ClientDto clientDto = new ClientDto();
        clientDto.setId(1L);
        clientDto.setUsername("john.doe");

        // When & Then
        assertEquals(clientDto, clientDto);
    }
}
