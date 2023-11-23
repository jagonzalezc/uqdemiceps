package co.edu.uniquindio.uqdemiceps;


import co.edu.uniquindio.uqdemiceps.dto.*;
import co.edu.uniquindio.uqdemiceps.dto.admin.*;
import co.edu.uniquindio.uqdemiceps.modelo.enums.Ciudad;
import co.edu.uniquindio.uqdemiceps.modelo.enums.Especialidad;
import co.edu.uniquindio.uqdemiceps.modelo.enums.EstadoPqrs;
import co.edu.uniquindio.uqdemiceps.servicios.interfaces.AdministradorServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
public class AdministradorTest {

    @Autowired
    private AdministradorServicio administradorServicio;

    @Test
    @Sql("classpath:dataset.sql" )
    public void registrarMedico() throws Exception{

        HorarioDTO horarioDTO = new HorarioDTO(
                "Lunes",
                LocalTime.of(8,00),
                LocalTime.of(17,00)
        );
        List<HorarioDTO> listaHorario = new ArrayList<>();

        listaHorario.add(horarioDTO);
        RegistroMedicoDTO registroMedicoDTO =new RegistroMedicoDTO(
                "juan",
                "86587",
                "ARMENIA",
                "CARDIOLOGIA",
                "76886",
                "juan@gmail.com",
                "1",
                "urlFoto",
                listaHorario

        );

        int nuevo= administradorServicio.crearMedico(registroMedicoDTO);

        Assertions.assertNotEquals(0,nuevo);
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void eliminarMedico() throws Exception{
        administradorServicio.eliminarMedico(1);
        Assertions.assertThrows(Exception.class, () -> administradorServicio.verDetalleMedico(1));
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void modificarMedico() throws Exception{
        DetalleMedicoDTO detalleMedicoDTO = administradorServicio.verDetalleMedico(1);

        DetalleMedicoDTO modificado = new DetalleMedicoDTO(
                1,
                detalleMedicoDTO.nombre(),
                detalleMedicoDTO.cedula(),
                detalleMedicoDTO.ciudad(),
                detalleMedicoDTO.telefono(),
                "mariap@gmail.com",
                detalleMedicoDTO.urlFoto(),
                detalleMedicoDTO.especialidad(),
                detalleMedicoDTO.horarios()
        );

        administradorServicio.actualizarMedico(modificado);

        DetalleMedicoDTO objetoModificado = administradorServicio.verDetalleMedico(1);

        Assertions.assertEquals("mariap@gmail.com", objetoModificado.email());
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void buscarMedico() throws Exception{
        DetalleMedicoDTO detalleMedicoDTO = administradorServicio.verDetalleMedico(2);

        Assertions.assertEquals(detalleMedicoDTO.codigo(), 2);
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void listarMedicos() throws Exception{
        List<ItemMedicoDTO> medicos = administradorServicio.listarMedicos();
        medicos.forEach(System.out::println);

        Assertions.assertEquals(5, medicos.size());
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void ListarPqrsTest() throws Exception{
        List<ItemPqrsDTO> listaPqrs = administradorServicio.listarPQRS();
        listaPqrs.forEach(System.out::println);
        Assertions.assertEquals(5, listaPqrs.size());
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void verDetallePqrs()throws Exception{
        DetallePqrsDTO detallePQRSDTO = administradorServicio.verDetallePQRS(2);
        System.out.println(detallePQRSDTO.toString());
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void responderPqrs() throws Exception{
        RegistroRespuestaDTO registroRespuestaDTO= new RegistroRespuestaDTO(
                6,
                2,
                1,
                "solicitud en curso, esteremos en contacto"
        );
        administradorServicio.responderPQRS(registroRespuestaDTO);
    }
    @Test
    @Sql("classpath:dataset.sql" )
    public void cambiarEstadoPqrs() throws Exception{
        administradorServicio.cambiarEstadoPQRS(2, EstadoPqrs.INACTIVO);
        Assertions.assertEquals(1, EstadoPqrs.INACTIVO.ordinal());
    }
    @Test
    @Sql("classpath:dataset.sql" )
    public void verHistorialConsultas() throws Exception{
        List<ItemCitaAdminDTO> historialConsultas = administradorServicio.listarCitas();

        historialConsultas.forEach(System.out::println);

        Assertions.assertEquals(5, historialConsultas.size());

    }
}

