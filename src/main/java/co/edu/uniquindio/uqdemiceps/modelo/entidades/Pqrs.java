package co.edu.uniquindio.uqdemiceps.modelo.entidades;

import co.edu.uniquindio.uqdemiceps.modelo.enums.EstadoPqrs;
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
public class Pqrs implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigoPqrs;
    @Lob
    private String descripcion;
    private String tipo;
    @Column(nullable = false)
    private EstadoPqrs estadoPqrs;
    @Column(nullable = false)
    private LocalDateTime fechaCreacion;
    @Column(nullable = false)
    @OneToMany (mappedBy = "pqrsMensaje")
    private List<Mensaje> mensajes;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Cita citaPqrs;



}
