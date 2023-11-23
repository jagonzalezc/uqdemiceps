package co.edu.uniquindio.uqdemiceps.repositorios;

import co.edu.uniquindio.uqdemiceps.modelo.entidades.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CuentaRepo extends JpaRepository<Cuenta, Integer> {
    Cuenta findByEmail(String email);
    Optional<Cuenta> findByEmailAndPassword(String email, String password);
}
