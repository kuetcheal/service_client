package com.javathinked.example.demo_spring.mapper;

import com.javathinked.example.demo_spring.dto.ClientDto;
import com.javathinked.example.demo_spring.model.Client;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientMapperEdgeCasesTest {

    @Test
    void shouldMapClientToDtoWithMaxValues() {
        // Given
        Client client = new Client("a".repeat(255), "a".repeat(255), "a".repeat(255), "a".repeat(255), "a".repeat(255), "a".repeat(255), "a".repeat(255));
        client.setId(Long.MAX_VALUE);
        client.setCreatedAt(LocalDateTime.MAX);

        // When
        ClientDto dto = ClientMapper.toDto(client);

        // Then
        assertNotNull(dto);
        assertEquals(Long.MAX_VALUE, dto.getId());
        assertEquals("a".repeat(255), dto.getUsername());
        assertEquals("a".repeat(255), dto.getFirstName());
        assertEquals("a".repeat(255), dto.getLastName());
        assertEquals("a".repeat(255), dto.getPostalCode());
        assertEquals("a".repeat(255), dto.getCity());
        assertEquals("a".repeat(255), dto.getCompanyName());
        assertEquals("a".repeat(255), dto.getProfile());
    }

    @Test
    void shouldMapClientToDtoWithMinValues() {
        // Given
        Client client = new Client("", "", "", "", "", "", "");
        client.setId(Long.MIN_VALUE);
        client.setCreatedAt(LocalDateTime.MIN);

        // When
        ClientDto dto = ClientMapper.toDto(client);

        // Then
        assertNotNull(dto);
        assertEquals(Long.MIN_VALUE, dto.getId());
        assertEquals("", dto.getUsername());
        assertEquals("", dto.getFirstName());
        assertEquals("", dto.getLastName());
        assertEquals("", dto.getPostalCode());
        assertEquals("", dto.getCity());
        assertEquals("", dto.getCompanyName());
        assertEquals("", dto.getProfile());
    }

    @Test
    void shouldMapDtoToClientWithMaxValues() {
        // Given
        ClientDto dto = new ClientDto();
        dto.setId(Long.MAX_VALUE);
        dto.setUsername("a".repeat(255));
        dto.setFirstName("a".repeat(255));
        dto.setLastName("a".repeat(255));
        dto.setPostalCode("a".repeat(255));
        dto.setCity("a".repeat(255));
        dto.setCompanyName("a".repeat(255));
        dto.setProfile("a".repeat(255));

        // When
        Client client = ClientMapper.toEntity(dto);

        // Then
        assertNotNull(client);
        assertEquals(Long.MAX_VALUE, client.getId());
        assertEquals("a".repeat(255), client.getUsername());
        assertEquals("a".repeat(255), client.getFirstName());
        assertEquals("a".repeat(255), client.getLastName());
        assertEquals("a".repeat(255), client.getPostalCode());
        assertEquals("a".repeat(255), client.getCity());
        assertEquals("a".repeat(255), client.getCompanyName());
        assertEquals("a".repeat(255), client.getProfile());
        assertNull(client.getCreatedAt());
    }

    @Test
    void shouldMapDtoToClientWithMinValues() {
        // Given
        ClientDto dto = new ClientDto();
        dto.setId(Long.MIN_VALUE);
        dto.setUsername("");
        dto.setFirstName("");
        dto.setLastName("");
        dto.setPostalCode("");
        dto.setCity("");
        dto.setCompanyName("");
        dto.setProfile("");

        // When
        Client client = ClientMapper.toEntity(dto);

        // Then
        assertNotNull(client);
        assertEquals(Long.MIN_VALUE, client.getId());
        assertEquals("", client.getUsername());
        assertEquals("", client.getFirstName());
        assertEquals("", client.getLastName());
        assertEquals("", client.getPostalCode());
        assertEquals("", client.getCity());
        assertEquals("", client.getCompanyName());
        assertEquals("", client.getProfile());
        assertNull(client.getCreatedAt());
    }

    @Test
    void shouldMapClientWithUnicodeCharacters() {
        // Given
        Client client = new Client("用户@域名.中国", "张", "三", "100000", "北京市", "公司名称", "用户简介");
        client.setId(1L);

        // When
        ClientDto dto = ClientMapper.toDto(client);

        // Then
        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("用户@域名.中国", dto.getUsername());
        assertEquals("张", dto.getFirstName());
        assertEquals("三", dto.getLastName());
        assertEquals("100000", dto.getPostalCode());
        assertEquals("北京市", dto.getCity());
        assertEquals("公司名称", dto.getCompanyName());
        assertEquals("用户简介", dto.getProfile());
    }

    @Test
    void shouldMapClientWithEmojiCharacters() {
        // Given
        Client client = new Client("user@domain.com", "John", "Doe", "75001", "Paris", "Company", "Profile with emoji 🚀");
        client.setId(1L);

        // When
        ClientDto dto = ClientMapper.toDto(client);

        // Then
        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("user@domain.com", dto.getUsername());
        assertEquals("John", dto.getFirstName());
        assertEquals("Doe", dto.getLastName());
        assertEquals("75001", dto.getPostalCode());
        assertEquals("Paris", dto.getCity());
        assertEquals("Company", dto.getCompanyName());
        assertEquals("Profile with emoji 🚀", dto.getProfile());
    }

    @Test
    void shouldMapClientWithSpecialCharacters() {
        // Given
        Client client = new Client("user@domain.com", "José", "D'Angelo", "75001", "Paris-15ème", "Compagnie & Cie", "Profil avec accents éàç");
        client.setId(1L);

        // When
        ClientDto dto = ClientMapper.toDto(client);

        // Then
        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("user@domain.com", dto.getUsername());
        assertEquals("José", dto.getFirstName());
        assertEquals("D'Angelo", dto.getLastName());
        assertEquals("75001", dto.getPostalCode());
        assertEquals("Paris-15ème", dto.getCity());
        assertEquals("Compagnie & Cie", dto.getCompanyName());
        assertEquals("Profil avec accents éàç", dto.getProfile());
    }

    @Test
    void shouldMapClientWithNumericStrings() {
        // Given
        Client client = new Client("123456", "123", "456", "789", "012", "345", "678");
        client.setId(1L);

        // When
        ClientDto dto = ClientMapper.toDto(client);

        // Then
        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("123456", dto.getUsername());
        assertEquals("123", dto.getFirstName());
        assertEquals("456", dto.getLastName());
        assertEquals("789", dto.getPostalCode());
        assertEquals("012", dto.getCity());
        assertEquals("345", dto.getCompanyName());
        assertEquals("678", dto.getProfile());
    }

    @Test
    void shouldMapClientWithWhitespaceOnly() {
        // Given
        Client client = new Client("   ", "   ", "   ", "   ", "   ", "   ", "   ");
        client.setId(1L);

        // When
        ClientDto dto = ClientMapper.toDto(client);

        // Then
        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("   ", dto.getUsername());
        assertEquals("   ", dto.getFirstName());
        assertEquals("   ", dto.getLastName());
        assertEquals("   ", dto.getPostalCode());
        assertEquals("   ", dto.getCity());
        assertEquals("   ", dto.getCompanyName());
        assertEquals("   ", dto.getProfile());
    }

    @Test
    void shouldMapClientWithNewlinesAndTabs() {
        // Given
        Client client = new Client("user\n@domain.com", "John\n", "Doe\t", "75001", "Paris\n", "Company\t", "Profile\nwith\ttabs");
        client.setId(1L);

        // When
        ClientDto dto = ClientMapper.toDto(client);

        // Then
        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("user\n@domain.com", dto.getUsername());
        assertEquals("John\n", dto.getFirstName());
        assertEquals("Doe\t", dto.getLastName());
        assertEquals("75001", dto.getPostalCode());
        assertEquals("Paris\n", dto.getCity());
        assertEquals("Company\t", dto.getCompanyName());
        assertEquals("Profile\nwith\ttabs", dto.getProfile());
    }

    @Test
    void shouldMapClientWithVeryLongStrings() {
        // Given
        String veryLongString = "a".repeat(1000);
        Client client = new Client(veryLongString, veryLongString, veryLongString, veryLongString, veryLongString, veryLongString, veryLongString);
        client.setId(1L);

        // When
        ClientDto dto = ClientMapper.toDto(client);

        // Then
        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals(veryLongString, dto.getUsername());
        assertEquals(veryLongString, dto.getFirstName());
        assertEquals(veryLongString, dto.getLastName());
        assertEquals(veryLongString, dto.getPostalCode());
        assertEquals(veryLongString, dto.getCity());
        assertEquals(veryLongString, dto.getCompanyName());
        assertEquals(veryLongString, dto.getProfile());
    }

    @Test
    void shouldMapClientWithMixedCase() {
        // Given
        Client client = new Client("User@Domain.COM", "JOHN", "doe", "75001", "PARIS", "Company", "PROFILE");
        client.setId(1L);

        // When
        ClientDto dto = ClientMapper.toDto(client);

        // Then
        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("User@Domain.COM", dto.getUsername());
        assertEquals("JOHN", dto.getFirstName());
        assertEquals("doe", dto.getLastName());
        assertEquals("75001", dto.getPostalCode());
        assertEquals("PARIS", dto.getCity());
        assertEquals("Company", dto.getCompanyName());
        assertEquals("PROFILE", dto.getProfile());
    }

    @Test
    void shouldMapClientWithSpecialSymbols() {
        // Given
        Client client = new Client("user@domain.com", "John", "Doe", "75001", "Paris", "Company", "Profile with symbols !@#$%^&*()_+-=[]{}|;':\",./<>?");
        client.setId(1L);

        // When
        ClientDto dto = ClientMapper.toDto(client);

        // Then
        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("user@domain.com", dto.getUsername());
        assertEquals("John", dto.getFirstName());
        assertEquals("Doe", dto.getLastName());
        assertEquals("75001", dto.getPostalCode());
        assertEquals("Paris", dto.getCity());
        assertEquals("Company", dto.getCompanyName());
        assertEquals("Profile with symbols !@#$%^&*()_+-=[]{}|;':\",./<>?", dto.getProfile());
    }
}
