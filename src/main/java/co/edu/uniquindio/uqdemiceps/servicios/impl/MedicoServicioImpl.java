package co.edu.uniquindio.uqdemiceps.servicios.impl;

import co.edu.uniquindio.uqdemiceps.dto.ItemCitaMedicoDTO;
import co.edu.uniquindio.uqdemiceps.dto.admin.ItemCitaAdminDTO;
import co.edu.uniquindio.uqdemiceps.modelo.entidades.Cita;
import co.edu.uniquindio.uqdemiceps.modelo.entidades.DiaLibre;
import co.edu.uniquindio.uqdemiceps.modelo.entidades.Medico;
import co.edu.uniquindio.uqdemiceps.modelo.enums.EstadoCita;
import co.edu.uniquindio.uqdemiceps.repositorios.*;
import co.edu.uniquindio.uqdemiceps.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.uqdemiceps.servicios.interfaces.MedicoServicio;
import co.edu.uniquindio.uqdemiceps.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicoServicioImpl implements MedicoServicio {


    private final CitaRepo citaRepo;
    private final MedicoRepo medicoRepo;
    private final DiaLibreRepo diaLibreRepo;

    @Override
    public List<ItemCitaAdminDTO> listarCitasPendientes(int codigoMedico) {
        List<Cita> listaCitasPendientes = citaRepo.findAllByEstadoCita(EstadoCita.PENDIENTE);
        List<Cita> listaCitasPendientesConCodigo = listaCitasPendientes
                .stream()
                .filter(cita -> cita.getEstadoCita() == EstadoCita.PENDIENTE && cita.getMedicoCita().getCodigo()==codigoMedico)
                .collect(Collectors.toList());

        List<ItemCitaAdminDTO> respuesta = new ArrayList<>();

        for( Cita c: listaCitasPendientesConCodigo ){

            respuesta.add( new ItemCitaAdminDTO(
                    c.getCodigoCita(),
                    c.getPacienteCita().getCedula(),
                    c.getPacienteCita().getNombre(),
                    c.getMedicoCita().getNombre(),
                    c.getMedicoCita().getEspecialidad(),
                    c.getEstadoCita(),
                    c.getFechaAtencion(),
                    c.getMotivo()
            ) );
        }
        return respuesta;
    }

    @Override
    public ItemCitaMedicoDTO atenderCita(int codigo) throws Exception {
        Optional<Cita> opcional =citaRepo.findById(codigo);

        if( opcional.isEmpty() ){
            throw new Exception("No existe una Cita con el código "+codigo);
        }
        Cita buscado = opcional.get();
        EstadoCita estadoCita= EstadoCita.ATENDIDA;
        return new ItemCitaMedicoDTO(
                buscado.getCodigoCita(),
                estadoCita,
                buscado.getMotivo(),
                buscado.getPacienteCita(),
                buscado.getFechaRegistro(),
                buscado.getFechaAtencion()
        );
    }
    @Override
    public List<ItemCitaAdminDTO> listarCitasPacienteMedico(int codigoPaciente, int codigoMedico) throws Exception {

        List<Cita> listaCitasPaciente = citaRepo.findByPacienteCitaCodigo(codigoPaciente);
        List<Cita> listaCitasPacienteMedico = listaCitasPaciente
                .stream()
                .filter(cita -> cita.getEstadoCita() == EstadoCita.ACTIVO && cita.getMedicoCita().getCodigo() == codigoMedico)
                .collect(Collectors.toList());

        List<ItemCitaAdminDTO> respuesta = new ArrayList<>();

        for( Cita c: listaCitasPacienteMedico ){

            respuesta.add( new ItemCitaAdminDTO(
                    c.getCodigoCita(),
                    c.getPacienteCita().getCedula(),
                    c.getPacienteCita().getNombre(),
                    c.getMedicoCita().getNombre(),
                    c.getMedicoCita().getEspecialidad(),
                    c.getEstadoCita(),
                    c.getFechaAtencion(),
                    c.getMotivo()

            ) );

        }

        return respuesta;
    }

    @Override
    public LocalDate agendarDiaLibre(int codigo, LocalDate dia) throws Exception {
        //busca citas del medico
        List<Cita> listaCitasMedico = citaRepo.findAllByMedicoCitaCodigo(codigo);

        //verifica que no hay citas el dia libre
        List<Cita> listaCitasDia = listaCitasMedico
                .stream()
                .filter(cita -> cita.getFechaAtencion().toLocalDate() == dia)
                .collect(Collectors.toList());
        Optional<DiaLibre> diasLibres=diaLibreRepo.findByMedico(codigo);
        DiaLibre diaLibre = new DiaLibre();
        if(listaCitasDia.size()<1 && diasLibres.isEmpty()){
            Optional<Medico> opcional =medicoRepo.findById(codigo);


            diaLibre.setDia(dia);
            diaLibre.setMedico(opcional.get());
            diaLibreRepo.save(diaLibre);

        }
        return diaLibre.getDia();

    }

    @Override
    public List<ItemCitaAdminDTO> listarCitasRealizadasMedico(int codigoMedico) throws Exception {
        List<Cita> listaCitasAtendidas = citaRepo.findAllByEstadoCita(EstadoCita.ATENDIDA);
        List<Cita> listaCitasPendientesConCodigo = listaCitasAtendidas

                .stream()
                .filter(cita -> cita.getEstadoCita() == EstadoCita.ATENDIDA && cita.getMedicoCita().getCodigo()==codigoMedico)
                .collect(Collectors.toList());

        List<ItemCitaAdminDTO> respuesta = new ArrayList<>();

        for( Cita c: listaCitasPendientesConCodigo ){
            respuesta.add( new ItemCitaAdminDTO(
                    c.getCodigoCita(),
                    c.getPacienteCita().getCedula(),
                    c.getPacienteCita().getNombre(),
                    c.getMedicoCita().getNombre(),
                    c.getMedicoCita().getEspecialidad(),
                    c.getEstadoCita(),
                    c.getFechaAtencion(),
                    c.getMotivo()
            ) );

        }

        return respuesta;
    }
}
