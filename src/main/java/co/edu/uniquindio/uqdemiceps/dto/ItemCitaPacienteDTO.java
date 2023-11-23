package co.edu.uniquindio.uqdemiceps.dto;

import co.edu.uniquindio.uqdemiceps.modelo.enums.EstadoCita;

import java.time.LocalDateTime;

public record ItemCitaPacienteDTO(

        int codigo,
        EstadoCita estadoCita,
        String motivo,

        LocalDateTime fechaAtencion,
        LocalDateTime fechaRegistro

) {
}
