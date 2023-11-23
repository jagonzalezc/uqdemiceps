package co.edu.uniquindio.uqdemiceps.dto;

import co.edu.uniquindio.uqdemiceps.modelo.entidades.Paciente;
import co.edu.uniquindio.uqdemiceps.modelo.enums.EstadoCita;

import java.time.LocalDateTime;

public record ItemCitaMedicoDTO(

        int codigo,
        EstadoCita estadoCita,
        String motivo,
        Paciente pacienteCita,

        LocalDateTime fechaAtencion,
        LocalDateTime fechaRegistro

) {
}
