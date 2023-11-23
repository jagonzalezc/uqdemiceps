package co.edu.uniquindio.uqdemiceps.modelo.entidades;

import co.edu.uniquindio.uqdemiceps.modelo.enums.Especialidad;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Medico extends Persona implements Serializable {


    @OneToMany(mappedBy = "medicoCita")
    private List<Cita> citas;

    @OneToMany (mappedBy = "medico")
    private List<DiaLibre> diasLibres;
    @OneToMany (mappedBy = "medico")
    private List<HorarioMedico> Horarios;

    private Especialidad especialidad;


}
