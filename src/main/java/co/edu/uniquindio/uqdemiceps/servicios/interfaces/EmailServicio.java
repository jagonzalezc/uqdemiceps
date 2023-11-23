package co.edu.uniquindio.uqdemiceps.servicios.interfaces;

import co.edu.uniquindio.uqdemiceps.dto.EmailDTO;

public interface EmailServicio {

    EmailDTO enviarEmail(EmailDTO emailDTO) throws Exception;

}
