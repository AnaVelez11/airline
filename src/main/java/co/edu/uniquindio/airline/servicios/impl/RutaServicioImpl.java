package co.edu.uniquindio.airline.servicios.impl;

import co.edu.uniquindio.airline.modelo.Ruta;
import co.edu.uniquindio.airline.repositorios.RutaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class RutaServicioImpl {

    @Autowired
    private RutaRepositorio rutaRepositorio;

    public RutaServicioImpl(RutaRepositorio rutaRepositorio) {
        this.rutaRepositorio = rutaRepositorio;
    }


}
