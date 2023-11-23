package co.edu.uniquindio.uqdemiceps.servicios.interfaces;

import java.util.List;

public interface ClinicaServicio {


    List<String> listarCiudades();
    List<String> listarEspecialidades();

    List<String> listarEps ();

    List<String> listarTipoSangre();
    List<String> listarAlergias();
}
