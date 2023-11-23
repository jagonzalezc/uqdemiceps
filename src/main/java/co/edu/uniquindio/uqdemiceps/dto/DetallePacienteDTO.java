package co.edu.uniquindio.uqdemiceps.dto;

import co.edu.uniquindio.uqdemiceps.modelo.entidades.Alergia;
import co.edu.uniquindio.uqdemiceps.modelo.entidades.Eps;
import co.edu.uniquindio.uqdemiceps.modelo.enums.Ciudad;
import co.edu.uniquindio.uqdemiceps.modelo.enums.TipoSangre;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record DetallePacienteDTO(
        @NotNull
        int codigo,
        @NotBlank
        @Length(max = 200)
        String nombre,
        @NotBlank
        @Length(max = 10)
        String cedula,
        @NotBlank
        String ciudad,
        @NotNull
        int eps,
        @NotBlank
        @Length(max = 20)
        String telefono,
        @NotBlank
        @Email
        @Length(max = 80)
        String email,

        @NotNull
        LocalDate fechaNacimiento,
        @NotBlank
        String urlFoto,

        @NotNull
        int alergia,
        @NotBlank
        String tipoSangre


) {
}