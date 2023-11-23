package co.edu.uniquindio.uqdemiceps.repositorios;

import co.edu.uniquindio.uqdemiceps.modelo.entidades.Cita;
import co.edu.uniquindio.uqdemiceps.modelo.enums.EstadoCita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CitaRepo extends JpaRepository<Cita, Integer> {
    List<Cita> findAllByMedicoCitaCodigo(int codigoMedico);

    List<Cita> findAllByEstadoCita(EstadoCita estadoCita);

    List<Cita> findAllByFechaRegistro(LocalDateTime fechaRegistro);
    List<Cita> findAllByFechaAtencion(LocalDate fechaAtencion);

    List<Cita> findByPacienteCitaCodigo(int codigo);

    @Query("select c from Cita c where c.estadoCita=0 and c.pacienteCita.codigo=:codigoPaciente")
    List<Cita> findByCitasActivas(int codigoPaciente);


}
