package co.edu.uniquindio.uqdemiceps.servicios.impl;

import co.edu.uniquindio.uqdemiceps.dto.DetallePqrsDTO;
import co.edu.uniquindio.uqdemiceps.dto.ItemPqrsDTO;
import co.edu.uniquindio.uqdemiceps.dto.RegistroRespuestaDTO;
import co.edu.uniquindio.uqdemiceps.dto.RespuestaDTO;
import co.edu.uniquindio.uqdemiceps.dto.admin.*;
import co.edu.uniquindio.uqdemiceps.modelo.entidades.*;
import co.edu.uniquindio.uqdemiceps.modelo.enums.*;
import co.edu.uniquindio.uqdemiceps.repositorios.*;
import co.edu.uniquindio.uqdemiceps.servicios.interfaces.AdministradorServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdministradorServicioImpl implements AdministradorServicio {

    private final MedicoRepo medicoRepo;
    private final PqrsRepo pqrsRepo;
    private final CuentaRepo cuentaRepo;
    private final MensajeRepo mensajeRepo;
    private final CitaRepo citaRepo;
    private final HorarioRepo horarioRepo;
    private final EpsRepo epsRepo;
    private final AlergiaRepo alergiaRepo;
    //@Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public int crearMedico(RegistroMedicoDTO medicoDTO) throws Exception {

        if( estaRepetidaCedula(medicoDTO.cedula()) ){
            throw new Exception("La cédula "+medicoDTO.cedula()+" ya está en uso");
        }

        if( estaRepetidoCorreo(medicoDTO.email()) ){
            throw new Exception("El email "+medicoDTO.cedula()+" ya está en uso");
        }

        Medico medico = new Medico();
        medico.setNombre(medicoDTO.nombre() );
        medico.setCedula(medicoDTO.cedula() );
        if (esCiudadValida(medicoDTO.ciudad())){
            throw new Exception("no existe la ciudad");
        }
        else{
            medico.setCiudad(Ciudad.valueOf(medicoDTO.ciudad()));


        }
        //datos de medico
        //buscar la especialidad con codigo
        if (esEspecialidadValida(medicoDTO.especialidad())){
            throw new Exception("no existe especialidad");
        }
        else{
            medico.setEspecialidad(Especialidad.valueOf(medicoDTO.especialidad()));

        }

        medico.setTelefono(medicoDTO.telefono());
        medico.setEmail(medicoDTO.email() );

        //encriptar la clave
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordEncriptada = passwordEncoder.encode( medicoDTO.password() );
        //asignar la clave encriptada
        medico.setPassword( passwordEncriptada );

        medico.setUrlFoto(medicoDTO.urlFoto());
        medico.setEstadoPersona(EstadoPersona.ACTIVO);

        Medico medicoNuevo = medicoRepo.save(medico);

        asignarHorariosMedico( medicoNuevo, medicoDTO.horarios() );

        return medicoNuevo.getCodigo();
    }

    private void asignarHorariosMedico(Medico medicoNuevo, List<HorarioDTO> horarios) {

        for( HorarioDTO h : horarios ){

            HorarioMedico hm = new HorarioMedico();
            hm.setDia( h.dia() );
            hm.setHoraInicio( h.horaInicio() );
            hm.setHoraFin( h.horaFin() );
            hm.setMedico( medicoNuevo );

            horarioRepo.save(hm);

        }

    }


    @Override
    public int actualizarMedico(DetalleMedicoDTO medicoDTO) throws Exception {

        Optional<Medico> opcional =medicoRepo.findById(medicoDTO.codigo());

        if( opcional.isEmpty() ){
            throw new Exception("No existe un médico con el código "+medicoDTO.codigo());
        }

        Medico buscado = opcional.get();
        buscado.setNombre(medicoDTO.nombre() );
        buscado.setCedula(medicoDTO.cedula() );
        if (esCiudadValida(medicoDTO.ciudad())){
            throw new Exception("no existe la ciudad");
        }
        else{
            buscado.setCiudad(Ciudad.valueOf(medicoDTO.ciudad()));

        }
        buscado.setTelefono(medicoDTO.telefono());
        buscado.setEmail(medicoDTO.email() );
        buscado.setUrlFoto(medicoDTO.urlFoto());

        if (esEspecialidadValida(medicoDTO.especialidad())){
            throw new Exception("no existe la especialidad");
        }
        else{
            buscado.setEspecialidad(Especialidad.valueOf(medicoDTO.especialidad()));

        }

        Medico medicoNuevo = medicoRepo.save(buscado);

        asignarHorariosMedico( medicoNuevo, medicoDTO.horarios() );

        return medicoNuevo.getCodigo();

    }

    @Override
    public void eliminarMedico(int codigo) throws Exception {

        Optional<Medico> opcional =medicoRepo.findById(codigo);

        if( opcional.isEmpty() ){
            throw new Exception("No existe un médico con el código "+codigo);
        }

        Medico buscado = opcional.get();
        buscado.setEstadoPersona(EstadoPersona.INACTIVO);
        System.out.println("prueba de eliminar medico, inactivo");
        medicoRepo.save( buscado );
        //medicoRepo.delete(buscado);

    }

    @Override
    public List<ItemMedicoDTO> listarMedicos() throws Exception {

        List<Medico> medicos = medicoRepo.findAll();

        if(medicos.isEmpty()){
            throw new Exception("No hay médicos registrados");
        }

        List<ItemMedicoDTO> respuesta = new ArrayList<>();

        for(Medico m: medicos){
            respuesta.add( new ItemMedicoDTO(
                    m.getCodigo(),
                    m.getCedula(),
                    m.getNombre(),
                    m.getUrlFoto(),
                    m.getEspecialidad()) );
        }

        return respuesta;
    }

    @Override
    public DetalleMedicoDTO verDetalleMedico(int codigo) throws Exception {

        Optional<Medico> opcional =medicoRepo.findById(codigo);

        if( opcional.isEmpty() || opcional.get().getEstadoPersona().equals(EstadoPersona.INACTIVO)){
            throw new Exception("No existe un médico con el código "+codigo);
        }

        Medico buscado = opcional.get();

        List<HorarioMedico> horarios = horarioRepo.findAllByMedicoCodigo(codigo);
        List<HorarioDTO> horariosDTO = new ArrayList<>();

        for( HorarioMedico h : horarios ){
            horariosDTO.add( new HorarioDTO(
                    h.getDia(),
                    h.getHoraInicio(),
                    h.getHoraFin()
            ) );
        }

        return new DetalleMedicoDTO(
                buscado.getCodigo(),
                buscado.getNombre(),
                buscado.getCedula(),
                buscado.getCiudad().toString(),
                buscado.getTelefono(),
                buscado.getEmail(),
                buscado.getUrlFoto(),
                buscado.getEspecialidad().toString(),
                horariosDTO
        );

    }



    @Override
    public List<ItemPqrsDTO> listarPQRS() throws Exception {

        List<Pqrs> listaPqrs = pqrsRepo.findAll();
        List<ItemPqrsDTO> respuesta = new ArrayList<>();

        for( Pqrs p: listaPqrs ){

            respuesta.add( new ItemPqrsDTO(
                    p.getCodigoPqrs(),
                    p.getEstadoPqrs(),
                    p.getDescripcion(),
                    p.getFechaCreacion(),
                    p.getCitaPqrs().getPacienteCita().getNombre()
            ) );

        }

        return respuesta;
    }

    @Override
    public DetallePqrsDTO verDetallePQRS(int codigo) throws Exception {

        Optional<Pqrs> opcional = pqrsRepo.findById(codigo);

        if(opcional.isEmpty()){
            throw new Exception("No existe un PQRS con el código "+codigo);
        }

        Pqrs buscado = opcional.get();
        List<Mensaje> mensajes = mensajeRepo.findAllByPqrsMensajeCodigoPqrs(codigo);

        return new DetallePqrsDTO(
                buscado.getCodigoPqrs(),
                buscado.getEstadoPqrs(),
                buscado.getDescripcion(),
                buscado.getCitaPqrs().getPacienteCita().getNombre(),
                buscado.getCitaPqrs().getMedicoCita().getNombre(),
                buscado.getCitaPqrs().getMedicoCita().getEspecialidad(),
                buscado.getFechaCreacion(),
                convertirRespuestasDTO(mensajes)
        );
    }

    private List<RespuestaDTO> convertirRespuestasDTO(List<Mensaje> mensajes) {
        return mensajes.stream().map(m -> new RespuestaDTO(
                m.getCodigo(),
                m.getContenido(),
                m.getCuenta().getEmail(),
                m.getFecha()
        )).toList();
    }

    @Override
    public int responderPQRS(RegistroRespuestaDTO registroRespuestaDTO) throws Exception {

        Optional<Pqrs> opcionalPQRS = pqrsRepo.findById(registroRespuestaDTO.codigoPQRS());

        if(opcionalPQRS.isEmpty()){
            throw new Exception("No existe un PQRS con el código "+registroRespuestaDTO.codigoPQRS());
        }

        Optional<Cuenta> opcionalCuenta = cuentaRepo.findById(registroRespuestaDTO.codigoCuenta());

        if(opcionalCuenta.isEmpty()){
            throw new Exception("No existe una cuenta con el código "+registroRespuestaDTO.codigoCuenta());
        }

        Mensaje mensajeNuevo = new Mensaje();
        mensajeNuevo.setPqrsMensaje(opcionalPQRS.get());
        mensajeNuevo.setFecha( LocalDateTime.now() );
        mensajeNuevo.setCuenta(opcionalCuenta.get());
        mensajeNuevo.setContenido(registroRespuestaDTO.mensaje() );

        Mensaje respuesta = mensajeRepo.save(mensajeNuevo);

        return respuesta.getCodigo();
    }

    @Override
    public void cambiarEstadoPQRS(int codigoPQRS, EstadoPqrs estadoPqrs) throws Exception {

        Optional<Pqrs> opcional = pqrsRepo.findById(codigoPQRS);

        if( opcional.isEmpty() ){
            throw new Exception("No existe un PQRS con el código "+codigoPQRS);
        }

        Pqrs pqrs = opcional.get();
        pqrs.setEstadoPqrs( estadoPqrs );

        pqrsRepo.save( pqrs );
    }

    @Override
    public List<ItemCitaAdminDTO> listarCitas() throws Exception {

        List<Cita> citas = citaRepo.findAll();
        List<ItemCitaAdminDTO> respuesta = new ArrayList<>();

        if(citas.isEmpty()){
            throw new Exception("No existen citas creadas");
        }

        for( Cita c : citas ){
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
    public int crearEps(RegistroEpsDTO epsDTO) throws Exception {

        if( estaRepetidaNombreEps(epsDTO.nombre()) ){
            throw new Exception("El nombre de eps "+epsDTO.nombre()+" ya está en uso");
        }

        Eps eps = new Eps();
        eps.setNombre(epsDTO.nombre() );

        Eps epsNuevo = epsRepo.save(eps);

        return epsNuevo.getCodigo();


    }

    @Override
    public int crearAlergia(RegistroAlergiaDTO alergiaDTO) throws Exception {

        if( estaRepetidaNombreAlergia(alergiaDTO.nombre()) ){
            throw new Exception("El nombre de alergia "+alergiaDTO.nombre()+" ya está en uso");
        }

        Alergia alergia = new Alergia();
        alergia.setNombre(alergiaDTO.nombre() );

        Alergia alergiaNuevo = alergiaRepo.save(alergia);

        return alergiaNuevo.getCodigo();


    }

    //funciones de utilidad
    private boolean estaRepetidoCorreo(String email) {
        return medicoRepo.findByEmail(email) != null;
    }

    private boolean estaRepetidaCedula(String cedula) {
        return medicoRepo.findByCedula(cedula) != null;
    }
    private boolean estaRepetidaNombreEps(String nombre) {return epsRepo.findByNombre(nombre) != null; }
    private boolean estaRepetidaNombreAlergia(String nombre) {return alergiaRepo.findByNombre(nombre) != null; }
    private boolean esCiudadValida(String ciudad) {
        if (EnumSet.allOf(Ciudad.class).contains(ciudad)){
            return  true;}
        else{
            return false;
        }
    }
    private boolean esEspecialidadValida(String especialidad) {
        if (EnumSet.allOf(Especialidad.class).contains(especialidad)){
            return  true;}
        else{
            return false;
        }
    }
}
