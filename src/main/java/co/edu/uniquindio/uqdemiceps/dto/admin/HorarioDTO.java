package co.edu.uniquindio.uqdemiceps.dto.admin;

import java.time.LocalTime;

public record HorarioDTO (
        String dia,
        LocalTime horaInicio,
        LocalTime horaFin) {
}
