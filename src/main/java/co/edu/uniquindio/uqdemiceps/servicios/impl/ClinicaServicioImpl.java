package co.edu.uniquindio.uqdemiceps.servicios.impl;

import co.edu.uniquindio.uqdemiceps.modelo.entidades.Alergia;
import co.edu.uniquindio.uqdemiceps.modelo.entidades.Eps;
import co.edu.uniquindio.uqdemiceps.modelo.enums.Ciudad;
import co.edu.uniquindio.uqdemiceps.modelo.enums.Especialidad;
import co.edu.uniquindio.uqdemiceps.modelo.enums.TipoSangre;
import co.edu.uniquindio.uqdemiceps.repositorios.AlergiaRepo;
import co.edu.uniquindio.uqdemiceps.repositorios.EpsRepo;
import co.edu.uniquindio.uqdemiceps.servicios.interfaces.ClinicaServicio;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ClinicaServicioImpl implements ClinicaServicio {

    private final EpsRepo epsRepo;
    private final AlergiaRepo alergiaRepo;
    @Override

    public List<String> listarCiudades() {
        return Arrays.stream(Ciudad.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> listarEspecialidades() {
        return Arrays.stream(Especialidad.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
    @Override
    public List<String> listarEps() {
        List<Eps> eps = epsRepo.findAll();
        List<String> nombresEps = new ArrayList<>();
        for (Eps epss : eps) {
            nombresEps.add(epss.getNombre());
        }
        return nombresEps;

    }

    @Override
    public List<String> listarTipoSangre() {
        return Arrays.stream(TipoSangre.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> listarAlergias() {
        List<Alergia> alergias = alergiaRepo.findAll();
        List<String> nombresAlergias = new ArrayList<>();
        for (Alergia alergia : alergias) {
            nombresAlergias.add(alergia.getNombre());
        }
        return nombresAlergias;
    }
}
