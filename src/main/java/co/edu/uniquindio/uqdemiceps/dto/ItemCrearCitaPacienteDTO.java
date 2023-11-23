package co.edu.uniquindio.uqdemiceps.dto;

import co.edu.uniquindio.uqdemiceps.modelo.enums.EstadoCita;

import co.edu.uniquindio.uqdemiceps.modelo.enums.EstadoCita;

import java.time.LocalDateTime;

public record ItemCrearCitaPacienteDTO(

    EstadoCita estadoCita,
    String motivo,

    int codigoPaciente,
    int codigoMedico,

    LocalDateTime fechaAtencion,
    LocalDateTime fechaRegistro
        )
{


}
