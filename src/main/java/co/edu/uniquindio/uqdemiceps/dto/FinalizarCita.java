package co.edu.uniquindio.uqdemiceps.dto;

public record FinalizarCita(
        String diagnostico,
        String notas_medicas,
        String tratamiento,
        int codigoCita
) {
}