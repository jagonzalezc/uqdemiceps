package co.edu.uniquindio.uqdemiceps.repositorios;

import co.edu.uniquindio.uqdemiceps.modelo.entidades.Cita;
import co.edu.uniquindio.uqdemiceps.modelo.entidades.DiaLibre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiaLibreRepo extends JpaRepository<DiaLibre, Integer> {
    @Query("SELECT  d FROM DiaLibre d WHERE  d.medico.codigo=:codigoMedico and d.dia>current_date ")
    Optional<DiaLibre> findByMedico(int codigoMedico);


}
