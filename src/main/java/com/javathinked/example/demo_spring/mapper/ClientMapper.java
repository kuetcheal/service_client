package com.javathinked.example.demo_spring.mapper;
import com.javathinked.example.demo_spring.dto.ClientDto;
import com.javathinked.example.demo_spring.model.Client;
import java.time.format.DateTimeFormatter;
public class ClientMapper {

    private static final DateTimeFormatter ISO = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    public static Client toEntity(ClientDto dto) {
        if (dto == null) return null;
        Client e = new Client();
        e.setId(dto.getId()); // ignoré en création (JPA gère)
        e.setUsername(dto.getUsername());
        e.setFirstName(dto.getFirstName());
        e.setLastName(dto.getLastName());
        e.setPostalCode(dto.getPostalCode());
        e.setCity(dto.getCity());
        e.setCompanyName(dto.getCompanyName());
        e.setProfile(dto.getProfile());
        // createdAt géré en @PrePersist dans l'entité
        return e;
    }
    public static ClientDto toDto(Client e) {
        if (e == null) return null;
        ClientDto dto = new ClientDto();
        dto.setId(e.getId());
        dto.setUsername(e.getUsername());
        dto.setFirstName(e.getFirstName());
        dto.setLastName(e.getLastName());
        dto.setPostalCode(e.getPostalCode());
        dto.setCity(e.getCity());
        dto.setCompanyName(e.getCompanyName());
        dto.setProfile(e.getProfile());
        dto.setCreatedAt(e.getCreatedAt() == null ? null : e.getCreatedAt().format(ISO));
        return dto;
    }
}
