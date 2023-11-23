package co.edu.uniquindio.uqdemiceps.modelo.entidades;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Mensaje implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;
    private String contenido;
    private String emisor;
    @Column(nullable = false)
    private LocalDateTime fecha;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Cuenta cuenta;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Pqrs pqrsMensaje;
    @OneToOne
    @JoinColumn( name = "mensajeId")
    private Mensaje mensajeAnterior;

}
