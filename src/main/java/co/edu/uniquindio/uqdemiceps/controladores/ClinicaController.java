package co.edu.uniquindio.uqdemiceps.controladores;

import co.edu.uniquindio.uqdemiceps.dto.MensajeDTO;
import co.edu.uniquindio.uqdemiceps.modelo.entidades.Alergia;
import co.edu.uniquindio.uqdemiceps.modelo.enums.Ciudad;
import co.edu.uniquindio.uqdemiceps.modelo.entidades.Eps;
import co.edu.uniquindio.uqdemiceps.modelo.enums.TipoSangre;
import co.edu.uniquindio.uqdemiceps.servicios.interfaces.ClinicaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/clinica")
@RequiredArgsConstructor
public class ClinicaController {
    private final ClinicaServicio clinicaServicio;

    @GetMapping("/listar-ciudades")
    public ResponseEntity<MensajeDTO> listarCiudades(){
        List<String> ciudades = clinicaServicio.listarCiudades();
        return ResponseEntity.ok().body(new MensajeDTO(false, ciudades));
    }
    @GetMapping("/listar-especialidades")
    public ResponseEntity<MensajeDTO> listarEpecialidades(){
        List<String> especialidades = clinicaServicio.listarEspecialidades();
        return ResponseEntity.ok().body(new MensajeDTO<>(false, especialidades));
    }

    @GetMapping("/listar-eps")
    public ResponseEntity<MensajeDTO> listarEps(){
        List<String> eps = clinicaServicio.listarEps();
        return ResponseEntity.ok().body(new MensajeDTO<>(false, eps));
    }

    @GetMapping("/listar-tipoSangre")
    public ResponseEntity<MensajeDTO> listarTipoSangre(){
        List<String> tipoSangre = clinicaServicio.listarTipoSangre();
        return ResponseEntity.ok().body(new MensajeDTO<>(false, tipoSangre));
    }

    @GetMapping("/listar-alergias")
    public ResponseEntity<MensajeDTO> listarAlergias(){
        List<String> alergias = clinicaServicio.listarAlergias();
        return ResponseEntity.ok().body(new MensajeDTO<>(false, alergias));
    }

}
