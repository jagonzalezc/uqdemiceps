package co.edu.uniquindio.uqdemiceps.dto;

import co.edu.uniquindio.uqdemiceps.dto.admin.HorarioDTO;

import java.util.List;

public record InfoMedicoDTO(
        int codigo,
        String nombre,
        String cedula,
        int codigoCiudad,
        int codigoEspecialidad,
        String telefono,
        String correo,
        List<HorarioDTO> horarios
) {
}
