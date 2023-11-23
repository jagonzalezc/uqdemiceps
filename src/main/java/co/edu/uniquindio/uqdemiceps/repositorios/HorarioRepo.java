package co.edu.uniquindio.uqdemiceps.repositorios;

import co.edu.uniquindio.uqdemiceps.modelo.entidades.HorarioMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorarioRepo extends JpaRepository<HorarioMedico, Integer> {

    List<HorarioMedico> findAllByMedicoCodigo(int codigo);

}
