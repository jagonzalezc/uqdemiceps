package co.edu.uniquindio.uqdemiceps.dto;

import java.time.LocalDateTime;

public record RespuestaDTO (
        int codigoMensaje,
        String mensaje,
        String nombreUsuario,
        LocalDateTime fecha) {
}
