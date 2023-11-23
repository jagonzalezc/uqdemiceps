package co.edu.uniquindio.uqdemiceps.modelo.entidades;

import co.edu.uniquindio.uqdemiceps.modelo.enums.TipoSangre;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor

public class Paciente extends Persona implements Serializable {



    @ManyToOne
    @JoinColumn(nullable = false)
    private Eps eps;
    @JoinColumn(nullable = false)
    private TipoSangre tipoSangre;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Alergia alergia;
    @Column(nullable = false)
    private LocalDate fechaNacimiento;
    @OneToMany(mappedBy = "pacienteCita")
    private List<Cita> citas;


}
