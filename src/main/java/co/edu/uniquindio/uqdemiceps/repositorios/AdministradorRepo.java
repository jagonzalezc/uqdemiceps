package co.edu.uniquindio.uqdemiceps.repositorios;

import co.edu.uniquindio.uqdemiceps.modelo.entidades.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepo extends JpaRepository<Administrador,String> {
}
