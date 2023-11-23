package co.edu.uniquindio.uqdemiceps.dto.admin;

import co.edu.uniquindio.uqdemiceps.modelo.enums.Especialidad;
import co.edu.uniquindio.uqdemiceps.modelo.enums.EstadoCita;

import java.time.LocalDateTime;

public record ItemCitaAdminDTO(

        int codigoCita,
        String cedulaPaciente,
        String nombrePaciente,
        String nombreMedico,
        Especialidad especialidad,
        EstadoCita estadoCita,
        LocalDateTime fechaAtencion,
        String motivo

) {
}
