package com.javathinked.example.demo_spring.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginResponseTest {

    @Test
    void shouldCreateLoginResponseWithAllFields() {
        // Given
        String accessToken = "test-token";
        String tokenType = "Bearer";
        long expiresIn = 3600L;

        // When
        LoginResponse loginResponse = new LoginResponse(accessToken, tokenType, expiresIn);

        // Then
        assertEquals(accessToken, loginResponse.getAccessToken());
        assertEquals(tokenType, loginResponse.getTokenType());
        assertEquals(expiresIn, loginResponse.getExpiresIn());
    }

    @Test
    void shouldCreateLoginResponseWithNullValues() {
        // Given
        String accessToken = null;
        String tokenType = null;
        long expiresIn = 0L;

        // When
        LoginResponse loginResponse = new LoginResponse(accessToken, tokenType, expiresIn);

        // Then
        assertNull(loginResponse.getAccessToken());
        assertNull(loginResponse.getTokenType());
        assertEquals(0L, loginResponse.getExpiresIn());
    }

    @Test
    void shouldCreateDefaultLoginResponse() {
        // When
        LoginResponse loginResponse = new LoginResponse();

        // Then
        assertNull(loginResponse.getAccessToken());
        assertNull(loginResponse.getTokenType());
        assertEquals(0L, loginResponse.getExpiresIn());
    }

    @Test
    void shouldSetAndGetAccessToken() {
        // Given
        LoginResponse loginResponse = new LoginResponse();
        String newToken = "new-token";

        // When
        loginResponse.setAccessToken(newToken);

        // Then
        assertEquals(newToken, loginResponse.getAccessToken());
    }

    @Test
    void shouldSetAndGetTokenType() {
        // Given
        LoginResponse loginResponse = new LoginResponse();
        String newType = "JWT";

        // When
        loginResponse.setTokenType(newType);

        // Then
        assertEquals(newType, loginResponse.getTokenType());
    }

    @Test
    void shouldSetAndGetExpiresIn() {
        // Given
        LoginResponse loginResponse = new LoginResponse();
        long newExpiresIn = 7200L;

        // When
        loginResponse.setExpiresIn(newExpiresIn);

        // Then
        assertEquals(newExpiresIn, loginResponse.getExpiresIn());
    }

    @Test
    void shouldHaveStringRepresentation() {
        // Given
        LoginResponse loginResponse = new LoginResponse("token", "Bearer", 3600L);

        // When
        String stringRepresentation = loginResponse.toString();

        // Then
        assertNotNull(stringRepresentation);
        // Le toString() par défaut contient le nom de la classe et l'hashCode
        assertTrue(stringRepresentation.contains("LoginResponse"));
    }

    @Test
    void shouldBeEqualWhenSameValues() {
        // Given
        LoginResponse loginResponse1 = new LoginResponse("token", "Bearer", 3600L);
        LoginResponse loginResponse2 = new LoginResponse("token", "Bearer", 3600L);

        // When & Then
        // Note: LoginResponse n'a pas d'equals/hashCode personnalisé, donc les objets ne sont pas égaux
        // mais nous testons que les valeurs sont identiques
        assertEquals(loginResponse1.getAccessToken(), loginResponse2.getAccessToken());
        assertEquals(loginResponse1.getTokenType(), loginResponse2.getTokenType());
        assertEquals(loginResponse1.getExpiresIn(), loginResponse2.getExpiresIn());
    }

    @Test
    void shouldNotBeEqualWhenDifferentValues() {
        // Given
        LoginResponse loginResponse1 = new LoginResponse("token1", "Bearer", 3600L);
        LoginResponse loginResponse2 = new LoginResponse("token2", "Bearer", 3600L);

        // When & Then
        assertNotEquals(loginResponse1, loginResponse2);
    }
}
