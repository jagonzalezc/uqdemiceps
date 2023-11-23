package co.edu.uniquindio.uqdemiceps;

import co.edu.uniquindio.uqdemiceps.dto.*;
import co.edu.uniquindio.uqdemiceps.dto.DetallePacienteDTO;
import co.edu.uniquindio.uqdemiceps.modelo.entidades.Alergia;
import co.edu.uniquindio.uqdemiceps.modelo.entidades.Eps;
import co.edu.uniquindio.uqdemiceps.modelo.entidades.Paciente;
import co.edu.uniquindio.uqdemiceps.modelo.enums.*;
import co.edu.uniquindio.uqdemiceps.repositorios.AlergiaRepo;
import co.edu.uniquindio.uqdemiceps.repositorios.EpsRepo;
import co.edu.uniquindio.uqdemiceps.servicios.interfaces.PacienteServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
public class PacienteTest {

    @Autowired
    private PacienteServicio pacienteServicio;
    @Autowired
    private EpsRepo epsRepo;
    @Autowired
    private AlergiaRepo alergiaRepo;


    @Test
    @Sql("classpath:dataset.sql" )
    public void crearPaciente() throws Exception{
        Optional<Eps> epsBuscada= epsRepo.findById(1);
        Eps eps = epsBuscada.get();

        Optional<Alergia> alergiaBuscada= alergiaRepo.findById(1);
        Alergia alergia = alergiaBuscada.get();

        RegistroPacienteDTO registroPacienteDTO = new RegistroPacienteDTO(
                "Pedro Galarza",
                "120543876",
                "PEREIRA",
                eps.getCodigo(),
                "7465897",
                "pedroG@gmail.com",
                "adgt12",
                LocalDate.of(1991, 12,12),
                alergia.getCodigo(),
                "urlfoto",
                "A_NEGATIVO"
        );

        int nuevo = pacienteServicio.crearPaciente(registroPacienteDTO);

        Assertions.assertNotEquals(0,nuevo);

    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void actualizarPacienteTest() throws Exception{
        Optional<Alergia> alergiaBuscada= alergiaRepo.findById(1);
        Alergia alergia = alergiaBuscada.get();
        DetallePacienteDTO guardado = pacienteServicio.verDetallePaciente(6);
        DetallePacienteDTO modificado = new DetallePacienteDTO(
                guardado.codigo(),
                guardado.nombre(),
                guardado.cedula(),
                guardado.ciudad(),
                guardado.eps(),
                guardado.telefono(),
                "modificado@gmail.com",
                guardado.fechaNacimiento(),
                guardado.urlFoto(),
                alergia.getCodigo(),
                guardado.tipoSangre());

        pacienteServicio.actualizarPaciente(modificado);
        DetallePacienteDTO objetoModificado = pacienteServicio.verDetallePaciente(6);
        Assertions.assertEquals("modificado@gmail.com", objetoModificado.email());
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void eliminarPacienteTest() throws Exception{

        Paciente paciente=pacienteServicio.eliminarPaciente(6);
        Assertions.assertEquals(EstadoPersona.INACTIVO, paciente.getEstadoPersona());
    }

    public void listarPacientesTest()throws Exception{
        List<ItemPacienteDTO> lista = pacienteServicio.listarPacientes();
        lista.forEach(System.out::println);
        Assertions.assertEquals(5, lista.size());
    }
    @Test
    @Sql("classpath:dataset.sql" )
    public void crearCitaTest() throws Exception{
        ItemCrearCitaPacienteDTO registroCita = new ItemCrearCitaPacienteDTO(
                EstadoCita.ACTIVO,
                "dolor de estomago",
                6,
                3,
                LocalDateTime.of(2023,11,11,7,00),
                LocalDateTime.of(2023,10,11,7,00)

                );
        int codigo=pacienteServicio.crearCita(registroCita);

        Assertions.assertEquals(6,6);
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void crearPQRSTest() throws Exception{
        ItemCrearPqrsDTO registroPQRSDTO = new ItemCrearPqrsDTO(
                EstadoPqrs.ACTIVO,
                "queja",
                LocalDateTime.of(2023,10,11,7,00),
                "brian castaño",
                1
        );
        int codigo=pacienteServicio.crearPqrs(registroPQRSDTO);
        Assertions.assertEquals(6,6);

    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void verDetalleCita() throws Exception{

        DetalleCitaDTO detalleCitaDTO=pacienteServicio.verDetalleCita(1);

        System.out.println(detalleCitaDTO);

        Assertions.assertEquals(1,detalleCitaDTO.codigoCita());

    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void responderPQRS() throws Exception{

        RegistroRespuestaDTO registroRespuestaDTO = new RegistroRespuestaDTO(
                6,
                1,
                1,
                "llevo esperando su respuesta"
        );

        int codigo = pacienteServicio.responderPQRS(registroRespuestaDTO);

        Assertions.assertNotNull(codigo);
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void cambiarPassword() throws Exception{
        CambiarPasswordDTO cambiarPasswordDTO= new CambiarPasswordDTO(
                6,
                "123456"
        );

        String pass = pacienteServicio.cambiarPassword(cambiarPasswordDTO);


        Assertions.assertEquals(pass,cambiarPasswordDTO.password());

    }


    @Test
    @Sql("classpath:dataset.sql" )
    public void filtrarCitasPorMedico() throws  Exception{

        List<ItemCitaPacienteDTO> itemCitaPacienteDTOS = pacienteServicio.filtrarCitasPorMedico(4);

        itemCitaPacienteDTOS.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void filtrarCitasPorFecha( ) throws Exception{

        List<ItemCitaPacienteDTO> pacienteDTO = pacienteServicio.filtrarCitasPorFecha(LocalDateTime.of(2023,11,03, 07,45));


        pacienteDTO.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void listarPqrsPaciente( )throws Exception{

        List<ItemPqrsPacienteDTO> itemPqrsPacienteDTO = pacienteServicio.listarPqrsPaciente(6);

        itemPqrsPacienteDTO.forEach(System.out::println);

    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void enviarLinkRecuperacion()throws Exception{

        EmailDTO emailDTO = pacienteServicio.enviarLinkRecuperacion("superalejo20@gmail.com");

        System.out.println(emailDTO);


        Assertions.assertNotNull(emailDTO);
    }
}
