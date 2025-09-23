package com.javathinked.example.demo_spring.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ClientAdvancedTest {

    private Client client;

    @BeforeEach
    void setUp() {
        client = new Client("john.doe", "John", "Doe", "75001", "Paris", "Acme Corp", "Standard");
    }

    @Test
    void shouldCreateClientWithAllArgsConstructor() {
        // Given
        Long id = 1L;
        String username = "jane.smith";
        String firstName = "Jane";
        String lastName = "Smith";
        String postalCode = "75002";
        String city = "Lyon";
        String companyName = "Tech Inc";
        String profile = "Premium";
        LocalDateTime createdAt = LocalDateTime.now();

        // When
        Client clientWithAllArgs = new Client(id, username, firstName, lastName, postalCode, city, companyName, profile);
        clientWithAllArgs.setCreatedAt(createdAt);

        // Then
        assertNotNull(clientWithAllArgs);
        assertEquals(id, clientWithAllArgs.getId());
        assertEquals(username, clientWithAllArgs.getUsername());
        assertEquals(firstName, clientWithAllArgs.getFirstName());
        assertEquals(lastName, clientWithAllArgs.getLastName());
        assertEquals(postalCode, clientWithAllArgs.getPostalCode());
        assertEquals(city, clientWithAllArgs.getCity());
        assertEquals(companyName, clientWithAllArgs.getCompanyName());
        assertEquals(profile, clientWithAllArgs.getProfile());
        assertEquals(createdAt, clientWithAllArgs.getCreatedAt());
    }

    @Test
    void shouldCreateClientWithAllArgsConstructorWithNullValues() {
        // Given
        Long id = null;
        String username = null;
        String firstName = null;
        String lastName = null;
        String postalCode = null;
        String city = null;
        String companyName = null;
        String profile = null;
        LocalDateTime createdAt = null;

        // When
        Client clientWithAllArgs = new Client(id, username, firstName, lastName, postalCode, city, companyName, profile);
        clientWithAllArgs.setCreatedAt(createdAt);

        // Then
        assertNotNull(clientWithAllArgs);
        assertNull(clientWithAllArgs.getId());
        assertNull(clientWithAllArgs.getUsername());
        assertNull(clientWithAllArgs.getFirstName());
        assertNull(clientWithAllArgs.getLastName());
        assertNull(clientWithAllArgs.getPostalCode());
        assertNull(clientWithAllArgs.getCity());
        assertNull(clientWithAllArgs.getCompanyName());
        assertNull(clientWithAllArgs.getProfile());
        assertNull(clientWithAllArgs.getCreatedAt());
    }

    @Test
    void shouldCreateClientWithAllArgsConstructorWithEmptyStrings() {
        // Given
        Long id = 1L;
        String username = "";
        String firstName = "";
        String lastName = "";
        String postalCode = "";
        String city = "";
        String companyName = "";
        String profile = "";
        LocalDateTime createdAt = LocalDateTime.now();

        // When
        Client clientWithAllArgs = new Client(id, username, firstName, lastName, postalCode, city, companyName, profile);
        clientWithAllArgs.setCreatedAt(createdAt);

        // Then
        assertNotNull(clientWithAllArgs);
        assertEquals(id, clientWithAllArgs.getId());
        assertEquals("", clientWithAllArgs.getUsername());
        assertEquals("", clientWithAllArgs.getFirstName());
        assertEquals("", clientWithAllArgs.getLastName());
        assertEquals("", clientWithAllArgs.getPostalCode());
        assertEquals("", clientWithAllArgs.getCity());
        assertEquals("", clientWithAllArgs.getCompanyName());
        assertEquals("", clientWithAllArgs.getProfile());
        assertEquals(createdAt, clientWithAllArgs.getCreatedAt());
    }

    @Test
    void shouldCreateClientWithAllArgsConstructorWithSpecialCharacters() {
        // Given
        Long id = 1L;
        String username = "user@domain.com";
        String firstName = "José";
        String lastName = "D'Angelo";
        String postalCode = "75001";
        String city = "Paris-15ème";
        String companyName = "Compagnie & Cie";
        String profile = "Profil avec accents éàç";
        LocalDateTime createdAt = LocalDateTime.now();

        // When
        Client clientWithAllArgs = new Client(id, username, firstName, lastName, postalCode, city, companyName, profile);
        clientWithAllArgs.setCreatedAt(createdAt);

        // Then
        assertNotNull(clientWithAllArgs);
        assertEquals(id, clientWithAllArgs.getId());
        assertEquals(username, clientWithAllArgs.getUsername());
        assertEquals(firstName, clientWithAllArgs.getFirstName());
        assertEquals(lastName, clientWithAllArgs.getLastName());
        assertEquals(postalCode, clientWithAllArgs.getPostalCode());
        assertEquals(city, clientWithAllArgs.getCity());
        assertEquals(companyName, clientWithAllArgs.getCompanyName());
        assertEquals(profile, clientWithAllArgs.getProfile());
        assertEquals(createdAt, clientWithAllArgs.getCreatedAt());
    }

    @Test
    void shouldCreateClientWithAllArgsConstructorWithUnicodeCharacters() {
        // Given
        Long id = 1L;
        String username = "用户@域名.中国";
        String firstName = "张";
        String lastName = "三";
        String postalCode = "100000";
        String city = "北京市";
        String companyName = "公司名称";
        String profile = "用户简介";
        LocalDateTime createdAt = LocalDateTime.now();

        // When
        Client clientWithAllArgs = new Client(id, username, firstName, lastName, postalCode, city, companyName, profile);
        clientWithAllArgs.setCreatedAt(createdAt);

        // Then
        assertNotNull(clientWithAllArgs);
        assertEquals(id, clientWithAllArgs.getId());
        assertEquals(username, clientWithAllArgs.getUsername());
        assertEquals(firstName, clientWithAllArgs.getFirstName());
        assertEquals(lastName, clientWithAllArgs.getLastName());
        assertEquals(postalCode, clientWithAllArgs.getPostalCode());
        assertEquals(city, clientWithAllArgs.getCity());
        assertEquals(companyName, clientWithAllArgs.getCompanyName());
        assertEquals(profile, clientWithAllArgs.getProfile());
        assertEquals(createdAt, clientWithAllArgs.getCreatedAt());
    }

    @Test
    void shouldCreateClientWithAllArgsConstructorWithEmojiCharacters() {
        // Given
        Long id = 1L;
        String username = "user@domain.com";
        String firstName = "John";
        String lastName = "Doe";
        String postalCode = "75001";
        String city = "Paris";
        String companyName = "Company";
        String profile = "Profile with emoji 🚀";
        LocalDateTime createdAt = LocalDateTime.now();

        // When
        Client clientWithAllArgs = new Client(id, username, firstName, lastName, postalCode, city, companyName, profile);
        clientWithAllArgs.setCreatedAt(createdAt);

        // Then
        assertNotNull(clientWithAllArgs);
        assertEquals(id, clientWithAllArgs.getId());
        assertEquals(username, clientWithAllArgs.getUsername());
        assertEquals(firstName, clientWithAllArgs.getFirstName());
        assertEquals(lastName, clientWithAllArgs.getLastName());
        assertEquals(postalCode, clientWithAllArgs.getPostalCode());
        assertEquals(city, clientWithAllArgs.getCity());
        assertEquals(companyName, clientWithAllArgs.getCompanyName());
        assertEquals(profile, clientWithAllArgs.getProfile());
        assertEquals(createdAt, clientWithAllArgs.getCreatedAt());
    }

    @Test
    void shouldCreateClientWithAllArgsConstructorWithVeryLongStrings() {
        // Given
        Long id = 1L;
        String veryLongString = "a".repeat(1000);
        String username = veryLongString;
        String firstName = veryLongString;
        String lastName = veryLongString;
        String postalCode = veryLongString;
        String city = veryLongString;
        String companyName = veryLongString;
        String profile = veryLongString;
        LocalDateTime createdAt = LocalDateTime.now();

        // When
        Client clientWithAllArgs = new Client(id, username, firstName, lastName, postalCode, city, companyName, profile);
        clientWithAllArgs.setCreatedAt(createdAt);

        // Then
        assertNotNull(clientWithAllArgs);
        assertEquals(id, clientWithAllArgs.getId());
        assertEquals(veryLongString, clientWithAllArgs.getUsername());
        assertEquals(veryLongString, clientWithAllArgs.getFirstName());
        assertEquals(veryLongString, clientWithAllArgs.getLastName());
        assertEquals(veryLongString, clientWithAllArgs.getPostalCode());
        assertEquals(veryLongString, clientWithAllArgs.getCity());
        assertEquals(veryLongString, clientWithAllArgs.getCompanyName());
        assertEquals(veryLongString, clientWithAllArgs.getProfile());
        assertEquals(createdAt, clientWithAllArgs.getCreatedAt());
    }

    @Test
    void shouldCreateClientWithAllArgsConstructorWithNumericStrings() {
        // Given
        Long id = 1L;
        String username = "123456";
        String firstName = "123";
        String lastName = "456";
        String postalCode = "789";
        String city = "012";
        String companyName = "345";
        String profile = "678";
        LocalDateTime createdAt = LocalDateTime.now();

        // When
        Client clientWithAllArgs = new Client(id, username, firstName, lastName, postalCode, city, companyName, profile);
        clientWithAllArgs.setCreatedAt(createdAt);

        // Then
        assertNotNull(clientWithAllArgs);
        assertEquals(id, clientWithAllArgs.getId());
        assertEquals(username, clientWithAllArgs.getUsername());
        assertEquals(firstName, clientWithAllArgs.getFirstName());
        assertEquals(lastName, clientWithAllArgs.getLastName());
        assertEquals(postalCode, clientWithAllArgs.getPostalCode());
        assertEquals(city, clientWithAllArgs.getCity());
        assertEquals(companyName, clientWithAllArgs.getCompanyName());
        assertEquals(profile, clientWithAllArgs.getProfile());
        assertEquals(createdAt, clientWithAllArgs.getCreatedAt());
    }

    @Test
    void shouldCreateClientWithAllArgsConstructorWithWhitespaceOnly() {
        // Given
        Long id = 1L;
        String username = "   ";
        String firstName = "   ";
        String lastName = "   ";
        String postalCode = "   ";
        String city = "   ";
        String companyName = "   ";
        String profile = "   ";
        LocalDateTime createdAt = LocalDateTime.now();

        // When
        Client clientWithAllArgs = new Client(id, username, firstName, lastName, postalCode, city, companyName, profile);
        clientWithAllArgs.setCreatedAt(createdAt);

        // Then
        assertNotNull(clientWithAllArgs);
        assertEquals(id, clientWithAllArgs.getId());
        assertEquals("   ", clientWithAllArgs.getUsername());
        assertEquals("   ", clientWithAllArgs.getFirstName());
        assertEquals("   ", clientWithAllArgs.getLastName());
        assertEquals("   ", clientWithAllArgs.getPostalCode());
        assertEquals("   ", clientWithAllArgs.getCity());
        assertEquals("   ", clientWithAllArgs.getCompanyName());
        assertEquals("   ", clientWithAllArgs.getProfile());
        assertEquals(createdAt, clientWithAllArgs.getCreatedAt());
    }

    @Test
    void shouldCreateClientWithAllArgsConstructorWithNewlinesAndTabs() {
        // Given
        Long id = 1L;
        String username = "user\n@domain.com";
        String firstName = "John\n";
        String lastName = "Doe\t";
        String postalCode = "75001";
        String city = "Paris\n";
        String companyName = "Company\t";
        String profile = "Profile\nwith\ttabs";
        LocalDateTime createdAt = LocalDateTime.now();

        // When
        Client clientWithAllArgs = new Client(id, username, firstName, lastName, postalCode, city, companyName, profile);
        clientWithAllArgs.setCreatedAt(createdAt);

        // Then
        assertNotNull(clientWithAllArgs);
        assertEquals(id, clientWithAllArgs.getId());
        assertEquals(username, clientWithAllArgs.getUsername());
        assertEquals(firstName, clientWithAllArgs.getFirstName());
        assertEquals(lastName, clientWithAllArgs.getLastName());
        assertEquals(postalCode, clientWithAllArgs.getPostalCode());
        assertEquals(city, clientWithAllArgs.getCity());
        assertEquals(companyName, clientWithAllArgs.getCompanyName());
        assertEquals(profile, clientWithAllArgs.getProfile());
        assertEquals(createdAt, clientWithAllArgs.getCreatedAt());
    }

    @Test
    void shouldCreateClientWithAllArgsConstructorWithSpecialSymbols() {
        // Given
        Long id = 1L;
        String username = "user@domain.com";
        String firstName = "John";
        String lastName = "Doe";
        String postalCode = "75001";
        String city = "Paris";
        String companyName = "Company";
        String profile = "Profile with symbols !@#$%^&*()_+-=[]{}|;':\",./<>?";
        LocalDateTime createdAt = LocalDateTime.now();

        // When
        Client clientWithAllArgs = new Client(id, username, firstName, lastName, postalCode, city, companyName, profile);
        clientWithAllArgs.setCreatedAt(createdAt);

        // Then
        assertNotNull(clientWithAllArgs);
        assertEquals(id, clientWithAllArgs.getId());
        assertEquals(username, clientWithAllArgs.getUsername());
        assertEquals(firstName, clientWithAllArgs.getFirstName());
        assertEquals(lastName, clientWithAllArgs.getLastName());
        assertEquals(postalCode, clientWithAllArgs.getPostalCode());
        assertEquals(city, clientWithAllArgs.getCity());
        assertEquals(companyName, clientWithAllArgs.getCompanyName());
        assertEquals(profile, clientWithAllArgs.getProfile());
        assertEquals(createdAt, clientWithAllArgs.getCreatedAt());
    }
}
