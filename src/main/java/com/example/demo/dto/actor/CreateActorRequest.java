package com.example.demo.dto.actor;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateActorRequest {
    @NotBlank
    String firstName;
    @NotBlank
    String lastName;

}
