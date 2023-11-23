package co.edu.uniquindio.uqdemiceps.modelo.entidades;

import co.edu.uniquindio.uqdemiceps.modelo.enums.Ciudad;
import co.edu.uniquindio.uqdemiceps.modelo.enums.EstadoPersona;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
@AllArgsConstructor
public class Persona extends Cuenta implements Serializable {

    @Column(length = 10,  nullable = false)
    private String cedula;
    @Column(length = 100, nullable = false)
    private String nombre;
    @Column(length = 10)
    private String telefono;
    @Column(nullable = false)
    private EstadoPersona estadoPersona;

    private Ciudad ciudad;

    private String urlFoto;


}

