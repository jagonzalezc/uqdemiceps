package co.edu.uniquindio.uqdemiceps.dto;

import java.time.LocalDateTime;
import java.util.List;

public record InfoPqrsDTO(
        int codigo,
        String estado,
        int codigoCita,
        String motivo,
        String nombrePaciente,
        LocalDateTime fecha,
        List<String> mensajes

) {
}
