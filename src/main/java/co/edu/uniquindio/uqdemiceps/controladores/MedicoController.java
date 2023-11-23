package co.edu.uniquindio.uqdemiceps.controladores;
import co.edu.uniquindio.uqdemiceps.dto.ItemPacienteDTO;
import co.edu.uniquindio.uqdemiceps.dto.MensajeDTO;
import co.edu.uniquindio.uqdemiceps.dto.DetallePacienteDTO;
import co.edu.uniquindio.uqdemiceps.dto.admin.DetalleMedicoDTO;
import co.edu.uniquindio.uqdemiceps.dto.admin.ItemCitaAdminDTO;
import co.edu.uniquindio.uqdemiceps.dto.admin.ItemMedicoDTO;
import co.edu.uniquindio.uqdemiceps.servicios.interfaces.AdministradorServicio;
import co.edu.uniquindio.uqdemiceps.servicios.interfaces.MedicoServicio;
import co.edu.uniquindio.uqdemiceps.servicios.interfaces.PacienteServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicos")
@RequiredArgsConstructor
public class MedicoController {
    private final MedicoServicio medicoServicio;
    private final AdministradorServicio administradorServicio;


    @PutMapping("/actualizar-medico")
    public ResponseEntity<MensajeDTO<String>> actualizarMedico(@Valid @RequestBody DetalleMedicoDTO  medicoDTO) throws Exception{
        administradorServicio.actualizarMedico(medicoDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Medico actualizado correctamente") );
    }

    @DeleteMapping("/eliminar/{codigo}")
    public ResponseEntity<MensajeDTO<String>> eliminarMedico(@PathVariable int codigo) throws
            Exception{
        administradorServicio.eliminarMedico(codigo);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "medico eliminado correctamete")
        );
    }

    @GetMapping("/detalle/{codigo}")
    public ResponseEntity<MensajeDTO<DetalleMedicoDTO>> verDetalleMedico(@PathVariable int
                                                                                     codigo) throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false,
                administradorServicio.verDetalleMedico(codigo)) );
    }
    @GetMapping("/listar-medicos")
    public ResponseEntity<MensajeDTO<List<ItemMedicoDTO>>> listarMedicos() throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, administradorServicio.listarMedicos()) );
    }

    @GetMapping("/listar-citas/{codigo}")
    public ResponseEntity<MensajeDTO> listarCitasMedico(@PathVariable int codigo) throws Exception {
        List<ItemCitaAdminDTO> citas=medicoServicio.listarCitasPendientes(codigo);
        return ResponseEntity.ok().body( new MensajeDTO(false, citas) );
    }
}