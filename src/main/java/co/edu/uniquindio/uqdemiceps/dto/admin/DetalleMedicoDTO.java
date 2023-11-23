package co.edu.uniquindio.uqdemiceps.dto.admin;

import co.edu.uniquindio.uqdemiceps.modelo.enums.Ciudad;
import co.edu.uniquindio.uqdemiceps.modelo.enums.Especialidad;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record DetalleMedicoDTO(
        @Positive
        int codigo,
        @NotEmpty
        @Length(max = 200)
        String nombre,
        @NotEmpty
        @Length(max = 10)
        String cedula,

        String ciudad,

        @NotEmpty
        @Length(max = 20)
        String telefono,
        @NotEmpty
        @Email
        @Length(max = 80)
        String email,
        @NotEmpty
        String urlFoto,
        String especialidad,
        @NotEmpty
        List<HorarioDTO> horarios


) {

}
