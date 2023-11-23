package co.edu.uniquindio.uqdemiceps.repositorios;

import co.edu.uniquindio.uqdemiceps.modelo.entidades.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepo extends JpaRepository<Medico, Integer> {

    Medico findByEmail(String email);

    Medico findByCedula(String cedula);
}
