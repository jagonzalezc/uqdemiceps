package co.edu.uniquindio.uqdemiceps.dto.admin;

import co.edu.uniquindio.uqdemiceps.modelo.enums.Ciudad;
import co.edu.uniquindio.uqdemiceps.modelo.enums.Especialidad;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record RegistroAlergiaDTO(
        @NotBlank
        @Length(max = 200)
        String nombre


) {
}