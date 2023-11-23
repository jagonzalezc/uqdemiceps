package co.edu.uniquindio.uqdemiceps.servicios.impl;

import co.edu.uniquindio.uqdemiceps.dto.FinalizarCita;
import co.edu.uniquindio.uqdemiceps.dto.ItemCitaMedicoDTO;
import co.edu.uniquindio.uqdemiceps.dto.MensajeDTO;
import co.edu.uniquindio.uqdemiceps.dto.admin.ItemCitaAdminDTO;
import co.edu.uniquindio.uqdemiceps.modelo.entidades.Atencion;
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
    private final AtencionRepo atencionRepo;

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
                buscado.getPacienteCita().getNombre(),
                buscado.getPacienteCita().getCedula(),
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

    @Override
    public MensajeDTO finalizarCita(FinalizarCita finalizarCitaDTO) throws Exception{
        Optional <Cita> citaBuscada = citaRepo.findById(finalizarCitaDTO.codigoCita());

        if(citaBuscada.isEmpty()){
            throw new Exception("Error, El código de cita "+finalizarCitaDTO.codigoCita()+ " No existe");
        }

        if(citaBuscada.get().getEstadoCita()==EstadoCita.PENDIENTE){
            Cita cita = citaBuscada.get();

            Atencion atencion = new Atencion();
            atencion.setCitaAtencion(cita);
            atencion.setDiagnostico(finalizarCitaDTO.diagnostico());
            atencion.setNotas(finalizarCitaDTO.notas_medicas());
            atencion.setTratamiento(finalizarCitaDTO.tratamiento());
            cita.setEstadoCita(EstadoCita.ATENDIDA);

            atencionRepo.save(atencion);

            citaRepo.save(cita);
        }else {
            throw new Exception("La cita "+citaBuscada.get().getCodigoCita()+" no está asignada");
        }
        return new MensajeDTO(false, "la cita ha terminado exitosamente");
    }

}
