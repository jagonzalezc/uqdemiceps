package co.edu.uniquindio.uqdemiceps.repositorios;

import co.edu.uniquindio.uqdemiceps.modelo.entidades.Pqrs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PqrsRepo extends JpaRepository<Pqrs, Integer> {

    List<Pqrs> findPqrsByCitaPqrsPacienteCitaCodigo(int citaPqrsPacienteCitaCodigo);
}
