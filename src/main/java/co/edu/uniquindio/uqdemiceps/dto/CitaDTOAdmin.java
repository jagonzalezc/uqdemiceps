package co.edu.uniquindio.uqdemiceps.dto;

import java.time.LocalDateTime;

public record CitaDTOAdmin(

        int codigoCita,
        String nombrePaciente,
        String nombreMedico,
        LocalDateTime fecha,
        String motivo

) {
}
