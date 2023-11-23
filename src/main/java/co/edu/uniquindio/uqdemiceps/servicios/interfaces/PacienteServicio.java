package co.edu.uniquindio.uqdemiceps.servicios.interfaces;

import co.edu.uniquindio.uqdemiceps.dto.*;
import co.edu.uniquindio.uqdemiceps.modelo.entidades.Paciente;

import java.time.LocalDateTime;
import java.util.List;

public interface PacienteServicio {

    int crearPaciente(RegistroPacienteDTO pacienteDTO) throws Exception;

    int actualizarPaciente(DetallePacienteDTO pacienteDTO) throws Exception;

    Paciente eliminarPaciente(int codigo) throws Exception;

    DetallePacienteDTO verDetallePaciente(int codigo) throws Exception;

    List<ItemPacienteDTO> listarPacientes() throws Exception;

    List<ItemCitaPacienteDTO> listarCitasPendientes() throws Exception;

    DetalleCitaDTO verDetalleCita(int codigo) throws Exception;

    int responderPQRS(RegistroRespuestaDTO registroRespuestaDTO) throws Exception;

    String cambiarPassword(CambiarPasswordDTO cambiarPasswordDTO) throws Exception;

    List<ItemCitaPacienteDTO> filtrarCitasPorMedico(int codigo) throws Exception;

    List<ItemCitaPacienteDTO> filtrarCitasPorFecha(LocalDateTime fechaRegistro) throws Exception;

    int crearPqrs(ItemCrearPqrsDTO pqrsDTO) throws Exception;

    List<ItemPqrsPacienteDTO> listarPqrsPaciente(int codigo) throws Exception;

    int crearCita(ItemCrearCitaPacienteDTO itemCitaPacienteDTO) throws Exception;

    EmailDTO enviarLinkRecuperacion(String email) throws Exception;








}
