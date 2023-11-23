package co.edu.uniquindio.uqdemiceps.dto;

import co.edu.uniquindio.uqdemiceps.modelo.entidades.Eps;

public record ItemPacienteDTO(

        int codigo,
        String cedula,
        String nombre,
        Eps eps


) {

}
