package com.javathinked.example.demo_spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javathinked.example.demo_spring.dto.ClientDto;
import com.javathinked.example.demo_spring.mapper.ClientMapper;
import com.javathinked.example.demo_spring.model.Client;
import com.javathinked.example.demo_spring.security.JwtAuthFilter;
import com.javathinked.example.demo_spring.security.SecurityConfig;
import com.javathinked.example.demo_spring.service.ClientService;
import com.javathinked.example.demo_spring.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ClientController.class)
@Import({SecurityConfig.class, ClientControllerTest.SecurityTestBeans.class})
class ClientControllerTest {

    private static final String TOKEN = "TEST_TOKEN";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    // On mocke JwtUtil (utilisé par JwtAuthFilter) pour valider notre token fictif
    @MockBean
    private JwtUtil jwtUtil;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setupJwt() {
        Mockito.when(jwtUtil.validate(TOKEN)).thenReturn(true);
        Mockito.when(jwtUtil.extractUsername(TOKEN)).thenReturn("tester");
        Mockito.when(jwtUtil.extractRoles(TOKEN)).thenReturn(List.of("ROLE_USER"));
    }

    @Test
    void testGetAllClients() throws Exception {
        Mockito.when(clientService.getAllClients()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/clients")
                .header("Authorization", "Bearer " + TOKEN))
               .andExpect(status().isOk())
               .andExpect(content().json("[]"));
    }

    @Test
    void testCreateClient() throws Exception {
        ClientDto inputDto = new ClientDto();
        inputDto.setUsername("john.doe");
        inputDto.setFirstName("John");
        inputDto.setLastName("Doe");

        Client savedEntity = ClientMapper.toEntity(inputDto);
        savedEntity.setId(1L);

        Mockito.when(clientService.createClient(any(Client.class))).thenReturn(savedEntity);

        mockMvc.perform(post("/api/clients")
                .header("Authorization", "Bearer " + TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDto)))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.username").value("john.doe"))
               .andExpect(jsonPath("$.firstName").value("John"))
               .andExpect(jsonPath("$.lastName").value("Doe"));
    }

    /**
     * Beans de sécu pour le test :
     * - on fournit un JwtAuthFilter RÉEL (qui utilisera le JwtUtil mocké)
     * - SecurityConfig est importé au-dessus
     */
    @TestConfiguration
    static class SecurityTestBeans {
        @Bean
        JwtAuthFilter jwtAuthFilter(JwtUtil jwtUtil) {
            return new JwtAuthFilter(jwtUtil);
        }
    }
}
