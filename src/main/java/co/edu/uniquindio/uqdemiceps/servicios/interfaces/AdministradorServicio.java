package co.edu.uniquindio.uqdemiceps.servicios.interfaces;

import co.edu.uniquindio.uqdemiceps.dto.DetallePacienteDTO;
import co.edu.uniquindio.uqdemiceps.dto.DetallePqrsDTO;
import co.edu.uniquindio.uqdemiceps.dto.ItemPqrsDTO;
import co.edu.uniquindio.uqdemiceps.dto.RegistroRespuestaDTO;
import co.edu.uniquindio.uqdemiceps.dto.admin.*;
import co.edu.uniquindio.uqdemiceps.dto.admin.ItemCitaAdminDTO;
import co.edu.uniquindio.uqdemiceps.modelo.entidades.Paciente;
import co.edu.uniquindio.uqdemiceps.modelo.enums.EstadoPqrs;

import java.util.List;
import java.util.Optional;

public interface AdministradorServicio {

    int crearMedico(RegistroMedicoDTO medicoDTO) throws Exception;

    int actualizarMedico(DetalleMedicoDTO medicoDTO) throws Exception;

    void eliminarMedico(int codigo) throws Exception;

    DetalleMedicoDTO verDetalleMedico(int codigo) throws Exception;

    List<ItemMedicoDTO> listarMedicos() throws Exception;

    List<ItemPqrsDTO> listarPQRS() throws Exception;

    DetallePqrsDTO verDetallePQRS(int codigo) throws Exception;

    int responderPQRS(RegistroRespuestaDTO registroRespuestaDTO) throws Exception;

    void cambiarEstadoPQRS(int codigoPQRS, EstadoPqrs estadoPQRS) throws Exception;

    List<ItemCitaAdminDTO> listarCitas() throws Exception;
    int crearEps(RegistroEpsDTO epsDTO) throws Exception;
    int crearAlergia(RegistroAlergiaDTO alergiaDTO) throws Exception;

}
