package co.edu.uniquindio.uqdemiceps.dto;

import co.edu.uniquindio.uqdemiceps.modelo.enums.Especialidad;
import co.edu.uniquindio.uqdemiceps.modelo.enums.EstadoCita;

import java.time.LocalDateTime;

public record DetalleCitaDTO(
        int codigoCita,
        EstadoCita estadoCita,
        String motivo,
        String cedulaPaciente,
        String nombrePaciente,
        String cedulaMedico,
        String nombreMedico,
        Especialidad especialidad,
        LocalDateTime fechaRegistro,
        LocalDateTime fechaAtencion

) {
}
