package co.edu.uniquindio.uqdemiceps.controladores;
import co.edu.uniquindio.uqdemiceps.dto.LoginDTO;
import co.edu.uniquindio.uqdemiceps.dto.MensajeDTO;
import co.edu.uniquindio.uqdemiceps.dto.RegistroPacienteDTO;
import co.edu.uniquindio.uqdemiceps.dto.admin.RegistroEpsDTO;
import co.edu.uniquindio.uqdemiceps.dto.admin.RegistroAlergiaDTO;

import co.edu.uniquindio.uqdemiceps.dto.TokenDTO;

import co.edu.uniquindio.uqdemiceps.dto.admin.RegistroMedicoDTO;
import co.edu.uniquindio.uqdemiceps.servicios.interfaces.AdministradorServicio;
import co.edu.uniquindio.uqdemiceps.servicios.interfaces.AutenticacionServicio;
import co.edu.uniquindio.uqdemiceps.servicios.interfaces.PacienteServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AutenticacionController {
    private final AutenticacionServicio autenticacionServicio;
    private final PacienteServicio pacienteServicio;
    private final AdministradorServicio administradorServicio;

    @PostMapping("/login")
    public ResponseEntity<MensajeDTO<TokenDTO>> login(@Valid @RequestBody LoginDTO loginDTO)
            throws Exception {
        TokenDTO tokenDTO = autenticacionServicio.login(loginDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, tokenDTO));
    }

    @PostMapping("/crear-paciente")
    public ResponseEntity<MensajeDTO<String>> crearPaciente(@Valid @RequestBody RegistroPacienteDTO
                                                                    pacienteDTO) throws Exception{
        pacienteServicio.crearPaciente(pacienteDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Paciente registrado correctamente") );
    }

    @PostMapping("/crear-eps")
    public ResponseEntity<MensajeDTO<String>> crearEps(@Valid @RequestBody RegistroEpsDTO
                                                                    epsDTO) throws Exception{
        administradorServicio.crearEps(epsDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "eps registrado correctamente") );
    }

    @PostMapping("/crear-alergia")
    public ResponseEntity<MensajeDTO<String>> crearAlergia(@Valid @RequestBody RegistroAlergiaDTO
                                                               alergiaDTO) throws Exception{
        administradorServicio.crearAlergia(alergiaDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Alergia registrada correctamente") );
    }

    @PostMapping("/crear-medico")
    public ResponseEntity<MensajeDTO<String>> crearMedico(@Valid @RequestBody RegistroMedicoDTO medicoDTO) throws Exception{
        administradorServicio.crearMedico(medicoDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "medico registrado correctamente") );
    }

}
