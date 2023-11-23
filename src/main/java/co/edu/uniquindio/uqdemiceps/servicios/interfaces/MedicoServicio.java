package co.edu.uniquindio.uqdemiceps.servicios.interfaces;

import co.edu.uniquindio.uqdemiceps.dto.ItemCitaMedicoDTO;
import co.edu.uniquindio.uqdemiceps.dto.MedicoDTO;
import co.edu.uniquindio.uqdemiceps.dto.admin.ItemCitaAdminDTO;

import java.time.LocalDate;
import java.util.List;

public interface MedicoServicio {

    List<ItemCitaAdminDTO> listarCitasPendientes(int codigo);

    ItemCitaMedicoDTO atenderCita(int codigo) throws Exception;


    //historial médico
    List<ItemCitaAdminDTO> listarCitasPacienteMedico(int codigoPaciente, int codigoMedico) throws Exception;

    LocalDate agendarDiaLibre(int codigo, LocalDate dia) throws Exception;

    List<ItemCitaAdminDTO> listarCitasRealizadasMedico(int codigoMedico) throws Exception;
}
