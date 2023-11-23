package co.edu.uniquindio.uqdemiceps.dto;

import co.edu.uniquindio.uqdemiceps.modelo.enums.Ciudad;
import co.edu.uniquindio.uqdemiceps.modelo.enums.TipoSangre;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record RegistroPacienteDTO(
        @NotBlank
        @Length(max = 200)
        String nombre,
        @NotBlank
        @Length(max = 10)
        String cedula,
        @NotBlank
        String ciudad,
        @NotNull
        String eps,
        @NotBlank
        @Length(max = 20)
        String telefono,
        @NotBlank
        @Email
        @Length(max = 80)
        String email,
        @NotBlank
        String password,
        @NotNull
        LocalDate fechaNacimiento,
        @NotNull
        String alergias,
        @NotBlank
        String urlFoto,
        @NotBlank
        String tipoSangre


) {
}
