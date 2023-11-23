package co.edu.uniquindio.uqdemiceps.dto;

import co.edu.uniquindio.uqdemiceps.modelo.entidades.Cuenta;

public record LoginDTO (String email, String password, Cuenta cuenta){

}
