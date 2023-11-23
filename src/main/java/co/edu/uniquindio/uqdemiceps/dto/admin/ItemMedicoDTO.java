package co.edu.uniquindio.uqdemiceps.dto.admin;

import co.edu.uniquindio.uqdemiceps.modelo.enums.Especialidad;

public record ItemMedicoDTO(int codigo,
                            String cedula,
                            String nombre,
                            String urlFoto,
                            Especialidad especialidad) {
}
