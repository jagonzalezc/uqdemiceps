package co.edu.uniquindio.uqdemiceps.servicios.impl;

import co.edu.uniquindio.uqdemiceps.dto.*;
import co.edu.uniquindio.uqdemiceps.modelo.entidades.*;
import co.edu.uniquindio.uqdemiceps.modelo.enums.Ciudad;
import co.edu.uniquindio.uqdemiceps.modelo.enums.EstadoPersona;
import co.edu.uniquindio.uqdemiceps.modelo.enums.TipoSangre;
import co.edu.uniquindio.uqdemiceps.repositorios.*;
import co.edu.uniquindio.uqdemiceps.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.uqdemiceps.servicios.interfaces.PacienteServicio;
import co.edu.uniquindio.uqdemiceps.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PacienteServicioImpl implements PacienteServicio {

    private final PacienteRepo pacienteRepo;
    private final MedicoRepo medicoRepo;
    private final PqrsRepo pqrsRepo;
    private final CuentaRepo cuentaRepo;
    private final JWTUtils jwtUtils;
    private final MensajeRepo mensajeRepo;
    private final CitaRepo citaRepo;
    private final EpsRepo epsRepo;
    private final AlergiaRepo alergiaRepo;
    private final EmailServicio emailServicio;
    private final HorarioRepo horarioRepo;
    //@Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public int crearPaciente(RegistroPacienteDTO pacienteDTO) throws Exception {

        if( estaRepetidaCedula(pacienteDTO.cedula()) ){
            throw new Exception("La cÃ©dula "+pacienteDTO.cedula()+" ya estÃ¡ en uso");
        }

        if( estaRepetidoCorreo(pacienteDTO.email()) ){
            throw new Exception("El email "+pacienteDTO.cedula()+" ya estÃ¡ en uso");
        }

        //Datos del Usuario
        Paciente paciente = new Paciente();
        paciente.setCedula(pacienteDTO.cedula() );
        paciente.setTelefono(pacienteDTO.telefono());
        paciente.setNombre(pacienteDTO.nombre() );
        paciente.setEmail(pacienteDTO.email() );

        if (esCiudadValida(pacienteDTO.ciudad())){
            throw new Exception("no existe la ciudad");
        }
        else{
            paciente.setCiudad(Ciudad.valueOf(pacienteDTO.ciudad()));


        }

        //encriptar la clave
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordEncriptada = passwordEncoder.encode( pacienteDTO.password() );
        //asignar la clave encriptada
        paciente.setPassword( passwordEncriptada );

        paciente.setUrlFoto(pacienteDTO.urlFoto());
        paciente.setEstadoPersona(EstadoPersona.ACTIVO);

        //Datos del Paciente
        paciente.setFechaNacimiento( pacienteDTO.fechaNacimiento() );
        //buscar la eps por codigo
        Eps epsBuscada=epsRepo.findByNombre(pacienteDTO.eps());
        paciente.setEps( epsBuscada );
        Alergia alergiaBuscada=alergiaRepo.findByNombre(pacienteDTO.alergias());
        paciente.setAlergia( alergiaBuscada);
        if (esTipoSangreValida(pacienteDTO.tipoSangre())){
            throw new Exception("no existe el tipo de sangre");
        }
        else{
            paciente.setTipoSangre(TipoSangre.valueOf(pacienteDTO.tipoSangre()));
        }
        Paciente pacienteNuevo = pacienteRepo.save(paciente);
        return pacienteNuevo.getCodigo();
    }



    @Override
    public int actualizarPaciente(DetallePacienteDTO pacienteDTO) throws Exception {

        Optional<Paciente> opcional =pacienteRepo.findById(pacienteDTO.codigo());

        if( opcional.isEmpty() ){
            throw new Exception("No existe un paciente con el código "+pacienteDTO.codigo());
        }

        Paciente buscado = opcional.get();

        buscado.setCedula(pacienteDTO.cedula() );
        buscado.setTelefono(pacienteDTO.telefono());
        buscado.setNombre(pacienteDTO.nombre() );
        buscado.setEmail(pacienteDTO.email() );
        buscado.setUrlFoto(pacienteDTO.urlFoto());

        if (esCiudadValida(pacienteDTO.ciudad())){
            throw new Exception("no existe la ciudad");
              }
        else{
            buscado.setCiudad(Ciudad.valueOf(pacienteDTO.ciudad()));

        }
        //Datos del Paciente
        buscado.setFechaNacimiento( pacienteDTO.fechaNacimiento() );
        //buscar la eps por codigo

        Optional<Eps> epsBuscada=epsRepo.findById(pacienteDTO.eps());
        if(epsBuscada.isEmpty()){
            throw new Exception("no existe la eps con el codigo");
        }
        buscado.setEps( epsBuscada.get() );

        Optional<Alergia> alergiaBuscada=alergiaRepo.findById(pacienteDTO.alergia());
        if(alergiaBuscada.isEmpty()){
            throw new Exception("no existe la alergia con el codigo");
        }
        buscado.setAlergia( alergiaBuscada.get() );

        if (esTipoSangreValida(pacienteDTO.tipoSangre())){
            throw new Exception("no existe el tipo de sangre");

        }
        else{
            buscado.setTipoSangre(TipoSangre.valueOf(pacienteDTO.tipoSangre()));

        }
        pacienteRepo.save( buscado );

        return buscado.getCodigo();
    }


    @Override
    public Paciente eliminarPaciente(int codigo) throws Exception {

        Optional<Paciente> opcional =pacienteRepo.findById(codigo);

        if( opcional.isEmpty() ){
            throw new Exception("No existe un PACIENTE con el código "+codigo);
        }

        Paciente buscado = opcional.get();
        buscado.setEstadoPersona(EstadoPersona.INACTIVO);
        pacienteRepo.save( buscado );
        return buscado;

        //pacienteRepo.delete(buscado);

    }

    @Override
    public List<ItemPacienteDTO> listarPacientes() throws Exception {

        List<Paciente> pacientes = pacienteRepo.findAll();

        if(pacientes.isEmpty()){
            throw new Exception("No hay pacientes registrados");
        }

        List<ItemPacienteDTO> respuesta = new ArrayList<>();

        for(Paciente p: pacientes){
            respuesta.add( new ItemPacienteDTO(
                    p.getCodigo(),
                    p.getCedula(),
                    p.getNombre(),
                    p.getEps()) );

        }

        return respuesta;
    }

    @Override
    public DetallePacienteDTO verDetallePaciente(int codigo) throws Exception {

        Optional<Paciente> opcional =pacienteRepo.findById(codigo);

        if( opcional.isEmpty() ){
            throw new Exception("No existe un PACIENTE con el código "+codigo);
        }

        Paciente buscado = opcional.get();

        return new DetallePacienteDTO(
                buscado.getCodigo(),
                buscado.getNombre(),
                buscado.getCedula(),
                buscado.getCiudad().toString(),

                buscado.getEps().getCodigo(),
                buscado.getTelefono(),
                buscado.getEmail(),
                buscado.getFechaNacimiento(),
                buscado.getUrlFoto(),
                buscado.getAlergia().getCodigo(),

                buscado.getTipoSangre().toString()
        );

    }

    @Override
    public int crearCita(ItemCrearCitaPacienteDTO itemCitaPacienteDTO) throws Exception {


        List<Cita> cantidadCitas=citaRepo.findByCitasActivas(itemCitaPacienteDTO.codigoPaciente());

        if(cantidadCitas.size()>3){
            throw new Exception("El paciente ya tiene asignadas 3 citas, no es posible asignar otra");
        }

        Optional<Paciente> paciente = pacienteRepo.findById(itemCitaPacienteDTO.codigoPaciente());
        Optional<Medico> medico = medicoRepo.findById(itemCitaPacienteDTO.codigoMedico());

        Cita cita = new Cita();
        cita.setEstadoCita(itemCitaPacienteDTO.estadoCita() );
        cita.setMotivo(itemCitaPacienteDTO.motivo());
        cita.setPacienteCita(paciente.get());
        cita.setMedicoCita(medico.get());
        cita.setFechaAtencion(itemCitaPacienteDTO.fechaAtencion());
        cita.setFechaRegistro(itemCitaPacienteDTO.fechaRegistro());
        Cita citaNuevo = citaRepo.save(cita);

        return citaNuevo.getCodigoCita();
    }
    @Override
    public List<ItemCitaPacienteDTO> listarCitasPendientes() throws Exception {

        List<Cita> listaCitas = citaRepo.findAll();
        List<ItemCitaPacienteDTO> respuesta = new ArrayList<>();

        for( Cita c: listaCitas ){

            respuesta.add( new ItemCitaPacienteDTO(
                    c.getCodigoCita(),
                    c.getEstadoCita(),
                    c.getMotivo(),
                    c.getFechaRegistro(),
                    c.getFechaAtencion()

                   ) );

        }

        return respuesta;
    }

    @Override
    public DetalleCitaDTO verDetalleCita(int codigo) throws Exception {

        Optional<Cita> opcional = citaRepo.findById(codigo);

        if(opcional.isEmpty()){
            throw new Exception("No existe un CITA con el código "+codigo);
        }

        Cita buscado = opcional.get();

        return new DetalleCitaDTO(
                buscado.getCodigoCita(),
                buscado.getEstadoCita(),
                buscado.getMotivo(),
                buscado.getPacienteCita().getCedula(),
                buscado.getPacienteCita().getNombre(),
                buscado.getMedicoCita().getCedula(),
                buscado.getMedicoCita().getNombre(),
                buscado.getMedicoCita().getEspecialidad(),
                buscado.getFechaRegistro(),
                buscado.getFechaAtencion()

        );
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
    public String cambiarPassword(CambiarPasswordDTO cambiarPasswordDTO) throws Exception {

        Optional<Paciente> opcional = pacienteRepo.findById(cambiarPasswordDTO.codigoPaciente());

        if( opcional.isEmpty() ){
            throw new Exception("No existe un Paciente con el código "+cambiarPasswordDTO.codigoPaciente());
        }

        Paciente paciente = opcional.get();
        paciente.setPassword( cambiarPasswordDTO.password() );

        pacienteRepo.save( paciente );


        return paciente.getPassword();
    }

    @Override
    public List<ItemCitaPacienteDTO> filtrarCitasPorMedico(int codigo) throws Exception {

        List<Cita> citas = citaRepo.findAllByMedicoCitaCodigo(codigo);
        List<ItemCitaPacienteDTO> respuesta = new ArrayList<>();

        if(citas.isEmpty()){
            throw new Exception("No existen citas creadas");
        }

        for( Cita c : citas ){
            respuesta.add( new ItemCitaPacienteDTO(
                    c.getCodigoCita(),
                    c.getEstadoCita(),
                    c.getMotivo(),
                    c.getFechaAtencion(),
                    c.getFechaRegistro()

            ) );
        }

        return respuesta;
    }

    @Override
    public List<ItemCitaPacienteDTO> filtrarCitasPorFecha(LocalDateTime fechaRegistro) throws Exception {

        List<Cita> citas = citaRepo.findAllByFechaRegistro(fechaRegistro);
        List<ItemCitaPacienteDTO> respuesta = new ArrayList<>();

        if(citas.isEmpty()){
            throw new Exception("No existen citas creadas");
        }

        for( Cita c : citas ){
            respuesta.add( new ItemCitaPacienteDTO(
                    c.getCodigoCita(),
                    c.getEstadoCita(),
                    c.getMotivo(),
                    c.getFechaAtencion(),
                    c.getFechaRegistro()

            ) );
        }

        return respuesta;
    }

@Override
public int crearPqrs(ItemCrearPqrsDTO pqrsDTO) throws Exception {

        Optional<Cita> citaBuscada = citaRepo.findById(pqrsDTO.codigoCita());

        //Datos de pqrs
        Pqrs pqrs = new Pqrs();
        pqrs.setEstadoPqrs(pqrsDTO.estadoPqrs() );
        pqrs.setTipo(pqrsDTO.tipo());
        pqrs.setFechaCreacion(pqrsDTO.fecha());
        pqrs.setCitaPqrs(citaBuscada.get());
        Pqrs pqrsNuevo = pqrsRepo.save(pqrs);

        return pqrsNuevo.getCodigoPqrs();
        }

    private boolean estaRepetidoCodigoPqrs(int codigo) {
        return pqrsRepo.findById(codigo) != null;
    }

    @Override
    public List<ItemPqrsPacienteDTO> listarPqrsPaciente(int codigo) throws Exception {

        List<Pqrs> listaPqrs = pqrsRepo.findPqrsByCitaPqrsPacienteCitaCodigo(codigo);
        List<ItemPqrsPacienteDTO> respuesta = new ArrayList<>();

        for( Pqrs p: listaPqrs ){

            respuesta.add( new ItemPqrsPacienteDTO(
                    p.getCodigoPqrs(),
                    p.getEstadoPqrs(),

                    p.getTipo(),
                    p.getFechaCreacion()

            ) );

        }

        return respuesta;
    }




    @Override
    public EmailDTO enviarLinkRecuperacion(String email) throws Exception {

        Cuenta optionalCuenta = cuentaRepo.findByEmail(email);

        if(optionalCuenta==null){
            throw new Exception("No existe una cuenta con el email "+email);
        }

        LocalTime fecha = LocalTime.now();

        String parametro = Base64.getEncoder().encodeToString((optionalCuenta.getCodigo()+": "+fecha).getBytes());

       EmailDTO emailDTO= emailServicio.enviarEmail( new EmailDTO(
                optionalCuenta.getEmail(),
                "Recuperacion de contraseña",
                "Hola, para recuperar tu contraseña ingresa al siquiente link: https://xxxxxx/recuperar-password/"+parametro

        ));

       return emailDTO;
    }

    private LocalDateTime calcularFechaExpiracion() {
        return LocalDateTime.now().plus(5, ChronoUnit.MINUTES);
    }

    private String crearToken(Cuenta cuenta){
        String rol;
        String nombre;
        if( cuenta instanceof Paciente){
            rol = "paciente";
            nombre = ((Paciente) cuenta).getNombre();
        }else if( cuenta instanceof Medico){
            rol = "medico";
            nombre = ((Medico) cuenta).getNombre();
        }else{
            rol = "admin";
            nombre = "Administrador";
        }
        Map<String, Object> map = new HashMap<>();
        map.put("rol", rol);
        map.put("nombre", nombre);
        map.put("id", cuenta.getCodigo());
        return jwtUtils.generarToken(cuenta.getEmail(), map);
    }


    private boolean estaRepetidoCorreo(String email) {
        return pacienteRepo.findByEmail(email) != null;
    }

    private boolean estaRepetidaCedula(String cedula) {
        return pacienteRepo.findByCedula(cedula) != null;
    }

    private boolean esCiudadValida(String ciudad) {
        if (EnumSet.allOf(Ciudad.class).contains(ciudad)){
            return  true;}
        else{
            return false;
        }
    }
    private boolean esTipoSangreValida(String tipoSangre) {
        if (EnumSet.allOf(TipoSangre.class).contains(tipoSangre)){
            return  true;}
        else{
            return false;
        }
    }

    private boolean estaRepetidoCodigoCita(int codigo) {
        return citaRepo.findById(codigo) != null;
    }
    private List<RespuestaDTO> convertirRespuestasDTO(List<Mensaje> mensajes) {
        return mensajes.stream().map(m -> new RespuestaDTO(
                m.getCodigo(),
                m.getContenido(),
                m.getCuenta().getEmail(),
                m.getFecha()
        )).toList();
    }
}

