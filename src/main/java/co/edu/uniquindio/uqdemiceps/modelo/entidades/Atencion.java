package co.edu.uniquindio.uqdemiceps.modelo.entidades;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Atencion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;
    @Column(length = 100, nullable = false)
    private String diagnostico;
    @Column(length = 100, nullable = false)
    @Lob
    private String tratamiento;
    @Lob
    private String notas;
    @JoinColumn(nullable = false)
    @OneToOne
    private Cita citaAtencion;
    @OneToMany(mappedBy = "atencionArchivo")
    private List<Archivo> archivos;
    @OneToOne
    private Lugar lugar;

}