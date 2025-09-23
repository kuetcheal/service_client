package com.javathinked.example.demo_spring.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class JwtUtilTest {

    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
        // Configuration des propriétés via ReflectionTestUtils
        ReflectionTestUtils.setField(jwtUtil, "secretBase64", "dGVzdC1zZWNyZXQta2V5LWZvci1qd3QtdG9rZW4tZ2VuZXJhdGlvbi10ZXN0aW5nLXB1cnBvc2VzLW9ubHk=");
        ReflectionTestUtils.setField(jwtUtil, "expirationMs", 3600000L); // 1 heure
    }

    @Test
    void shouldGenerateValidToken() {
        // Given
        String username = "testuser";
        List<String> roles = Arrays.asList("ROLE_USER", "ROLE_ADMIN");

        // When
        String token = jwtUtil.generate(username, roles);

        // Then
        assertNotNull(token);
        assertTrue(token.contains("."));
        assertTrue(jwtUtil.validate(token));
        assertEquals(username, jwtUtil.extractUsername(token));
        assertEquals(roles, jwtUtil.extractRoles(token));
    }

    @Test
    void shouldValidateValidToken() {
        // Given
        String username = "testuser";
        List<String> roles = Arrays.asList("ROLE_USER");
        String token = jwtUtil.generate(username, roles);

        // When & Then
        assertTrue(jwtUtil.validate(token));
    }

    @Test
    void shouldNotValidateInvalidToken() {
        // Given
        String invalidToken = "invalid.token.here";

        // When & Then
        assertFalse(jwtUtil.validate(invalidToken));
    }

    @Test
    void shouldNotValidateNullToken() {
        // When & Then
        assertFalse(jwtUtil.validate(null));
    }

    @Test
    void shouldNotValidateEmptyToken() {
        // When & Then
        assertFalse(jwtUtil.validate(""));
    }

    @Test
    void shouldExtractUsernameFromValidToken() {
        // Given
        String username = "testuser";
        List<String> roles = Arrays.asList("ROLE_USER");
        String token = jwtUtil.generate(username, roles);

        // When
        String extractedUsername = jwtUtil.extractUsername(token);

        // Then
        assertEquals(username, extractedUsername);
    }

    @Test
    void shouldExtractRolesFromValidToken() {
        // Given
        String username = "testuser";
        List<String> roles = Arrays.asList("ROLE_USER", "ROLE_ADMIN");
        String token = jwtUtil.generate(username, roles);

        // When
        List<String> extractedRoles = jwtUtil.extractRoles(token);

        // Then
        assertEquals(roles, extractedRoles);
    }

    @Test
    void shouldGetExpirationMs() {
        // When
        long expirationMs = jwtUtil.getExpirationMs();

        // Then
        assertEquals(3600000L, expirationMs);
    }

    @Test
    void shouldThrowExceptionWhenExtractingUsernameFromInvalidToken() {
        // Given
        String invalidToken = "invalid.token.here";

        // When & Then
        assertThrows(Exception.class, () -> jwtUtil.extractUsername(invalidToken));
    }

    @Test
    void shouldThrowExceptionWhenExtractingRolesFromInvalidToken() {
        // Given
        String invalidToken = "invalid.token.here";

        // When & Then
        assertThrows(Exception.class, () -> jwtUtil.extractRoles(invalidToken));
    }
}
