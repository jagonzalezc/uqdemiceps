package co.edu.uniquindio.uqdemiceps.servicios.interfaces;

import co.edu.uniquindio.uqdemiceps.dto.LoginDTO;
import co.edu.uniquindio.uqdemiceps.dto.TokenDTO;

public interface AutenticacionServicio {

    TokenDTO login(LoginDTO loginDTO) throws Exception;
}
