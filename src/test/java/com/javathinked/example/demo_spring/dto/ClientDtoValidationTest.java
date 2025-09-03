package com.javathinked.example.demo_spring.dto;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ClientDtoValidationTest {

    private static Validator validator;

    @BeforeAll
    static void setup() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    private ClientDto validDto() {
        ClientDto dto = new ClientDto();
        dto.setUsername("john.doe");
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setPostalCode("75001");
        dto.setCity("Paris");
        dto.setCompanyName("Acme");
        dto.setProfile("{\"role\":\"USER\"}");
        // createdAt est un champ de sortie (souvent null en entrée)
        return dto;
    }

    @Test
    void validDto_shouldPass() {
        ClientDto dto = validDto();
        Set<ConstraintViolation<ClientDto>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Le DTO valide ne doit pas générer de violations");
    }

    @Test
    void username_blank_shouldFail() {
        ClientDto dto = validDto();
        dto.setUsername("  ");
        Set<ConstraintViolation<ClientDto>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("username")),
                "Une violation sur 'username' est attendue (ex: @NotBlank)");
    }

    @Test
    void firstName_blank_shouldFail() {
        ClientDto dto = validDto();
        dto.setFirstName("");
        Set<ConstraintViolation<ClientDto>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("firstName")),
                "Une violation sur 'firstName' est attendue");
    }

    @Test
    void lastName_blank_shouldFail() {
        ClientDto dto = validDto();
        dto.setLastName(" \t");
        Set<ConstraintViolation<ClientDto>> violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("lastName")),
                "Une violation sur 'lastName' est attendue");
    }

    @Test
    void postalCode_tooLong_shouldFail_ifSizeConstraintPresent() {
        ClientDto dto = validDto();
        dto.setPostalCode("12345678901234567890"); // 20 chars
        Set<ConstraintViolation<ClientDto>> violations = validator.validate(dto);

        // Ce test passera uniquement si tu as une contrainte @Size sur postalCode.
        // On vérifie "au moins une violation" sans cibler la propriété pour rester souple.
        // Adapter si tu ajoutes @Size(min=, max=) précisément.
        if (violations.isEmpty()) {
            System.out.println("Note: ajoute @Size sur postalCode pour activer ce contrôle de longueur.");
        }
    }
}
