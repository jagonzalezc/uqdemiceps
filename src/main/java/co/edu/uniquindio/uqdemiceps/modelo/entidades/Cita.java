package co.edu.uniquindio.uqdemiceps.modelo.entidades;

import co.edu.uniquindio.uqdemiceps.modelo.enums.EstadoCita;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Cita implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigoCita;
    private EstadoCita estadoCita;
    @Column(nullable = false)
    private LocalDateTime fechaRegistro;
    @Column(nullable = false)
    private LocalDateTime fechaAtencion;
    @Column(length = 500)
    private String motivo;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Medico medicoCita;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Paciente pacienteCita;

    @OneToOne(mappedBy = "citaAtencion")
    private Atencion atencion;
    @OneToMany (mappedBy = "citaPqrs")
    private List<Pqrs> pqrs;


}