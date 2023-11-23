package co.edu.uniquindio.uqdemiceps.servicios.impl;

import co.edu.uniquindio.uqdemiceps.dto.LoginDTO;
import co.edu.uniquindio.uqdemiceps.dto.TokenDTO;
import co.edu.uniquindio.uqdemiceps.modelo.entidades.Cuenta;
import co.edu.uniquindio.uqdemiceps.modelo.entidades.Medico;
import co.edu.uniquindio.uqdemiceps.modelo.entidades.Paciente;
import co.edu.uniquindio.uqdemiceps.repositorios.CuentaRepo;
import co.edu.uniquindio.uqdemiceps.servicios.interfaces.AutenticacionServicio;
import co.edu.uniquindio.uqdemiceps.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
/*
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AutenticacionServicioImpl implements AutenticacionServicio {
    private final CuentaRepo cuentaRepo;

    @Override
    public Cuenta login(String email, String password) throws  Exception {
        Optional<Cuenta> cuenta=cuentaRepo.findByEmailAndPassword(email, password);
        if (cuenta.isEmpty()) {
    throw new Exception("los datos de autenticacion son incorrectos");
        }
        return cuenta.get();
    }
}
 */
@Service
@RequiredArgsConstructor
public class AutenticacionServicioImpl implements AutenticacionServicio {
    private final CuentaRepo cuentaRepo;
    private final JWTUtils jwtUtils;
    @Override
    public TokenDTO login(LoginDTO loginDTO) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Cuenta cuenta = cuentaRepo.findByEmail(loginDTO.email());
        if(cuenta.getEmail() ==null){
            throw new Exception("No existe el email ingresado");
        }

        if( passwordEncoder.matches(loginDTO.password(), cuenta.getPassword()) ){
            throw new Exception("La contraseña ingresada es incorrecta");
        }


        return new TokenDTO( crearToken(cuenta) );
    }
    private String crearToken(Cuenta cuenta){
        String rol;
        String nombre;
        if( cuenta instanceof Paciente){
            rol = "paciente";
            nombre = ((Paciente) cuenta).getNombre();
        }else if( cuenta instanceof Medico){
            rol = "medico";
            nombre = ((Medico) cuenta).getNombre();
        }else{
            rol = "admin";
            nombre = "Administrador";
        }
        Map<String, Object> map = new HashMap<>();
        map.put("rol", rol);
        map.put("nombre", nombre);
        map.put("id", cuenta.getCodigo());
        return jwtUtils.generarToken(cuenta.getEmail(), map);
    }
}
