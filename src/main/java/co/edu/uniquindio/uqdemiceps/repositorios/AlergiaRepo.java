package co.edu.uniquindio.uqdemiceps.repositorios;

import co.edu.uniquindio.uqdemiceps.modelo.entidades.Alergia;
import co.edu.uniquindio.uqdemiceps.modelo.entidades.Eps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlergiaRepo extends JpaRepository<Alergia, Integer> {
    Alergia findByNombre(String nombre);

}
