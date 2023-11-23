package co.edu.uniquindio.uqdemiceps.dto;

import co.edu.uniquindio.uqdemiceps.dto.admin.HorarioDTO;
import co.edu.uniquindio.uqdemiceps.modelo.entidades.DiaLibre;
import co.edu.uniquindio.uqdemiceps.modelo.enums.Ciudad;
import co.edu.uniquindio.uqdemiceps.modelo.enums.Especialidad;

import java.util.List;

public record MedicoDTO(
        int codigo,
        String nombre,
        String cedula,
        Ciudad codigoCiudad,
        Especialidad especialidad,
        String telefono,
        String correo,
        String password,
        List<HorarioDTO> horarios,
        List<DiaLibre> diasLibres

) {

}
