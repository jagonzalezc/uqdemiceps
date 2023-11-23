package co.edu.uniquindio.uqdemiceps.dto;

import jakarta.validation.constraints.NotBlank;
public record TokenDTO (
        @NotBlank
        String token
){
}