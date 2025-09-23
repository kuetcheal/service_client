package com.javathinked.example.demo_spring.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void shouldCreateClientWithAllFields() {
        // Given
        String username = "john.doe";
        String firstName = "John";
        String lastName = "Doe";
        String postalCode = "75001";
        String city = "Paris";
        String companyName = "Acme";
        String profile = "Client test";

        // When
        Client client = new Client(username, firstName, lastName, postalCode, city, companyName, profile);

        // Then
        assertEquals(username, client.getUsername());
        assertEquals(firstName, client.getFirstName());
        assertEquals(lastName, client.getLastName());
        assertEquals(postalCode, client.getPostalCode());
        assertEquals(city, client.getCity());
        assertEquals(companyName, client.getCompanyName());
        assertEquals(profile, client.getProfile());
        assertNull(client.getId());
        assertNull(client.getCreatedAt());
    }

    @Test
    void shouldCreateClientWithNullValues() {
        // Given
        String username = null;
        String firstName = null;
        String lastName = null;
        String postalCode = null;
        String city = null;
        String companyName = null;
        String profile = null;

        // When
        Client client = new Client(username, firstName, lastName, postalCode, city, companyName, profile);

        // Then
        assertNull(client.getUsername());
        assertNull(client.getFirstName());
        assertNull(client.getLastName());
        assertNull(client.getPostalCode());
        assertNull(client.getCity());
        assertNull(client.getCompanyName());
        assertNull(client.getProfile());
        assertNull(client.getId());
        assertNull(client.getCreatedAt());
    }

    @Test
    void shouldSetAndGetId() {
        // Given
        Client client = new Client("user", "First", "Last", "75001", "Paris", "Company", "Profile");
        Long id = 1L;

        // When
        client.setId(id);

        // Then
        assertEquals(id, client.getId());
    }

    @Test
    void shouldSetAndGetCreatedAt() {
        // Given
        Client client = new Client("user", "First", "Last", "75001", "Paris", "Company", "Profile");
        LocalDateTime now = LocalDateTime.now();

        // When
        client.setCreatedAt(now);

        // Then
        assertEquals(now, client.getCreatedAt());
    }

    @Test
    void shouldSetAndGetUsername() {
        // Given
        Client client = new Client("user", "First", "Last", "75001", "Paris", "Company", "Profile");
        String newUsername = "newuser";

        // When
        client.setUsername(newUsername);

        // Then
        assertEquals(newUsername, client.getUsername());
    }

    @Test
    void shouldSetAndGetFirstName() {
        // Given
        Client client = new Client("user", "First", "Last", "75001", "Paris", "Company", "Profile");
        String newFirstName = "NewFirst";

        // When
        client.setFirstName(newFirstName);

        // Then
        assertEquals(newFirstName, client.getFirstName());
    }

    @Test
    void shouldSetAndGetLastName() {
        // Given
        Client client = new Client("user", "First", "Last", "75001", "Paris", "Company", "Profile");
        String newLastName = "NewLast";

        // When
        client.setLastName(newLastName);

        // Then
        assertEquals(newLastName, client.getLastName());
    }

    @Test
    void shouldSetAndGetPostalCode() {
        // Given
        Client client = new Client("user", "First", "Last", "75001", "Paris", "Company", "Profile");
        String newPostalCode = "69000";

        // When
        client.setPostalCode(newPostalCode);

        // Then
        assertEquals(newPostalCode, client.getPostalCode());
    }

    @Test
    void shouldSetAndGetCity() {
        // Given
        Client client = new Client("user", "First", "Last", "75001", "Paris", "Company", "Profile");
        String newCity = "Lyon";

        // When
        client.setCity(newCity);

        // Then
        assertEquals(newCity, client.getCity());
    }

    @Test
    void shouldSetAndGetCompanyName() {
        // Given
        Client client = new Client("user", "First", "Last", "75001", "Paris", "Company", "Profile");
        String newCompanyName = "NewCompany";

        // When
        client.setCompanyName(newCompanyName);

        // Then
        assertEquals(newCompanyName, client.getCompanyName());
    }

    @Test
    void shouldSetAndGetProfile() {
        // Given
        Client client = new Client("user", "First", "Last", "75001", "Paris", "Company", "Profile");
        String newProfile = "NewProfile";

        // When
        client.setProfile(newProfile);

        // Then
        assertEquals(newProfile, client.getProfile());
    }

    @Test
    void shouldHaveStringRepresentation() {
        // Given
        Client client = new Client("john.doe", "John", "Doe", "75001", "Paris", "Acme", "Client test");
        client.setId(1L);

        // When
        String stringRepresentation = client.toString();

        // Then
        assertNotNull(stringRepresentation);
        // Le toString() par défaut contient le nom de la classe et l'hashCode
        assertTrue(stringRepresentation.contains("Client"));
    }
}
