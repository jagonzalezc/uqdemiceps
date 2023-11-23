package co.edu.uniquindio.uqdemiceps.dto;

public record MensajeDTO<T>(
        boolean error,
        T respuesta
) {
}
