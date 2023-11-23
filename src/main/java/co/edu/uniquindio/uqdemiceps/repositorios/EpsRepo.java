package co.edu.uniquindio.uqdemiceps.repositorios;

import co.edu.uniquindio.uqdemiceps.modelo.entidades.Eps;
import co.edu.uniquindio.uqdemiceps.modelo.entidades.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EpsRepo extends JpaRepository<Eps, Integer> {
    Eps findByNombre(String nombre);
    @Query("select e.nombre from Eps e")
    List<String> listarTodos();
}
