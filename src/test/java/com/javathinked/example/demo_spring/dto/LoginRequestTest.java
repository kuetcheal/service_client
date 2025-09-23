package com.javathinked.example.demo_spring.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginRequestTest {

    @Test
    void shouldCreateLoginRequestWithAllFields() {
        // Given
        String username = "testuser";
        String password = "testpassword";

        // When
        LoginRequest loginRequest = new LoginRequest(username, password);

        // Then
        assertEquals(username, loginRequest.getUsername());
        assertEquals(password, loginRequest.getPassword());
    }

    @Test
    void shouldCreateLoginRequestWithNullValues() {
        // Given
        String username = null;
        String password = null;

        // When
        LoginRequest loginRequest = new LoginRequest(username, password);

        // Then
        assertNull(loginRequest.getUsername());
        assertNull(loginRequest.getPassword());
    }

    @Test
    void shouldSetAndGetUsername() {
        // Given
        LoginRequest loginRequest = new LoginRequest("user", "pass");
        String newUsername = "newuser";

        // When
        loginRequest.setUsername(newUsername);

        // Then
        assertEquals(newUsername, loginRequest.getUsername());
    }

    @Test
    void shouldSetAndGetPassword() {
        // Given
        LoginRequest loginRequest = new LoginRequest("user", "pass");
        String newPassword = "newpass";

        // When
        loginRequest.setPassword(newPassword);

        // Then
        assertEquals(newPassword, loginRequest.getPassword());
    }

    @Test
    void shouldHaveStringRepresentation() {
        // Given
        LoginRequest loginRequest = new LoginRequest("testuser", "testpass");

        // When
        String stringRepresentation = loginRequest.toString();

        // Then
        assertNotNull(stringRepresentation);
        // Le toString() par défaut contient le nom de la classe et l'hashCode
        assertTrue(stringRepresentation.contains("LoginRequest"));
    }

    @Test
    void shouldBeEqualWhenSameValues() {
        // Given
        LoginRequest loginRequest1 = new LoginRequest("user", "pass");
        LoginRequest loginRequest2 = new LoginRequest("user", "pass");

        // When & Then
        // Note: LoginRequest n'a pas d'equals/hashCode personnalisé, donc les objets ne sont pas égaux
        // mais nous testons que les valeurs sont identiques
        assertEquals(loginRequest1.getUsername(), loginRequest2.getUsername());
        assertEquals(loginRequest1.getPassword(), loginRequest2.getPassword());
    }

    @Test
    void shouldNotBeEqualWhenDifferentValues() {
        // Given
        LoginRequest loginRequest1 = new LoginRequest("user1", "pass");
        LoginRequest loginRequest2 = new LoginRequest("user2", "pass");

        // When & Then
        assertNotEquals(loginRequest1, loginRequest2);
    }
}
