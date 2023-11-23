package co.edu.uniquindio.uqdemiceps.dto;

import co.edu.uniquindio.uqdemiceps.modelo.enums.EstadoPqrs;

import java.time.LocalDateTime;

public record ItemCrearPqrsDTO(
                          EstadoPqrs estadoPqrs,
                          String tipo,
                          LocalDateTime fecha,
                          String nombrePaciente,
                          int codigoCita){
}

