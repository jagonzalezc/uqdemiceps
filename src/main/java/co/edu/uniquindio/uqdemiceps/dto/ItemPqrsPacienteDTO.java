package co.edu.uniquindio.uqdemiceps.dto;

import co.edu.uniquindio.uqdemiceps.modelo.enums.EstadoPqrs;

import java.time.LocalDateTime;

public record ItemPqrsPacienteDTO(int codigo,
                          EstadoPqrs estadoPqrs,
                          String tipo,
                          LocalDateTime fecha){
}