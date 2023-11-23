package co.edu.uniquindio.uqdemiceps.controladores;
import co.edu.uniquindio.uqdemiceps.dto.ItemPacienteDTO;
import co.edu.uniquindio.uqdemiceps.dto.MensajeDTO;
import co.edu.uniquindio.uqdemiceps.dto.DetallePacienteDTO;
import co.edu.uniquindio.uqdemiceps.servicios.interfaces.PacienteServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@RequiredArgsConstructor
public class PacienteController {
    private final PacienteServicio pacienteServicio;

    @PutMapping("/actualizar-paciente")
    public ResponseEntity<MensajeDTO<String>> actualizarPaciente(@Valid @RequestBody DetallePacienteDTO
                                                                   pacienteDTO) throws Exception{
        pacienteServicio.actualizarPaciente(pacienteDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Paciente actualizado correctamete") );
    }

    @DeleteMapping("/eliminar/{codigo}")
    public ResponseEntity<MensajeDTO<String>> eliminarPaciente(@PathVariable int codigo) throws
            Exception{
        pacienteServicio.eliminarPaciente(codigo);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Paciente eliminado correctamete")
        );
    }
    @GetMapping("/detalle/{codigo}")
    public ResponseEntity<MensajeDTO<DetallePacienteDTO>> verDetallePaciente(@PathVariable int
                                                                                     codigo) throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false,
                pacienteServicio.verDetallePaciente(codigo)) );
    }
    @GetMapping("/listar-todos")
    public ResponseEntity<MensajeDTO<List<ItemPacienteDTO>>> listarTodos() throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, pacienteServicio.listarPacientes()) );
    }


}