package co.edu.uniquindio.uqdemiceps;

import co.edu.uniquindio.uqdemiceps.dto.ItemCitaMedicoDTO;
import co.edu.uniquindio.uqdemiceps.dto.MedicoDTO;
import co.edu.uniquindio.uqdemiceps.dto.admin.ItemCitaAdminDTO;
import co.edu.uniquindio.uqdemiceps.modelo.enums.Ciudad;
import co.edu.uniquindio.uqdemiceps.modelo.enums.Especialidad;
import co.edu.uniquindio.uqdemiceps.modelo.enums.EstadoPqrs;
import co.edu.uniquindio.uqdemiceps.servicios.interfaces.MedicoServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@Transactional
public class MedicoTest {

    @Autowired
    private  MedicoServicio medicoServicio;

    @Test
    @Sql("classpath:dataset.sql" )
    public void listarCitasPendientes () throws Exception{

        List<ItemCitaAdminDTO> listaCitas = medicoServicio.listarCitasPendientes(2);

        listaCitas.forEach(System.out::println);
        Assertions.assertEquals(1, listaCitas.size());
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void listarCitasPacienteMedico () throws Exception{

        List<ItemCitaAdminDTO> listaCitas = medicoServicio.listarCitasPacienteMedico(9, 3);

        listaCitas.forEach(System.out::println);
        Assertions.assertEquals(1, listaCitas.size());
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void atenderCita () throws Exception{

        ItemCitaMedicoDTO Cita = medicoServicio.atenderCita(3);

        Assertions.assertEquals(3, Cita.estadoCita().ordinal());

    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void agendarDiaLibre () throws Exception{

        LocalDate diaNuevo = medicoServicio.agendarDiaLibre(3, LocalDate.of(2023,12,12));
        Assertions.assertEquals(LocalDate.of(2023,12,12), diaNuevo);

    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void listarCitasRealizadas () throws Exception{

        List<ItemCitaAdminDTO> listaCitas = medicoServicio.listarCitasRealizadasMedico(3);

        listaCitas.forEach(System.out::println);
        Assertions.assertEquals(1, listaCitas.size());
    }
}
