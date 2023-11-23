package co.edu.uniquindio.uqdemiceps.repositorios;

import co.edu.uniquindio.uqdemiceps.modelo.entidades.Atencion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtencionRepo extends JpaRepository<Atencion,Integer> {
}
