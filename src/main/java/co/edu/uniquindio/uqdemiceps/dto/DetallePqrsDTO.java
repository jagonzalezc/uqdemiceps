package co.edu.uniquindio.uqdemiceps.dto;

import co.edu.uniquindio.uqdemiceps.modelo.enums.Especialidad;
import co.edu.uniquindio.uqdemiceps.modelo.enums.EstadoPqrs;

import java.time.LocalDateTime;
import java.util.List;

public record DetallePqrsDTO(
        int codigo,
        EstadoPqrs estado,
        String motivoPQRS,
        String nombrePaciente,
        String nombreMedico,
        Especialidad especialidad,
        LocalDateTime fecha,
        List<RespuestaDTO> mensajes

) {
}
