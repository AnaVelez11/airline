package co.edu.uniquindio.airline.servicios.impl;

import co.edu.uniquindio.airline.modelo.*;
import co.edu.uniquindio.airline.repositorios.AeronaveRepositorio;
import co.edu.uniquindio.airline.repositorios.DestinoRepositorio;
import co.edu.uniquindio.airline.servicios.interfaces.AeronaveServicio;
import co.edu.uniquindio.airline.servicios.interfaces.SillaServicio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@Service
@Transactional
public class AeronaveServicioImpl implements AeronaveServicio {

    private final AeronaveRepositorio aeronaveRepositorio;
    private final DestinoRepositorio destinoRepositorio;


    public AeronaveServicioImpl(AeronaveRepositorio aeronaveRepositorio, DestinoRepositorio destinoRepositorio) {
        this.aeronaveRepositorio = aeronaveRepositorio;
        this.destinoRepositorio = destinoRepositorio;

    }

    @Override
    // Método para agregar una nueva aeronave
    public Aeronave agregarAeronave(Aeronave aeronave) {
        aeronaveRepositorio.save(aeronave);
        return aeronave;
    }

    @Override
    public List<Aeronave> obtenerAeronavesDisponiblesPorRuta(String origen, String destino, Clase clase, int cantidadPasajeros, boolean esIdaVuelta, LocalDate fechaPartida, LocalDate fechaRetorno) {
        // Validar que el origen sea "CDMX"
        if (!"CDMX".equalsIgnoreCase(origen)) {
            throw new IllegalArgumentException("El origen debe ser CDMX");
        }
        if (destino == null || destino.isEmpty()) {
            throw new IllegalArgumentException("El destino no puede ser nulo o vacío");
        }
        if (cantidadPasajeros <= 0) {
            throw new IllegalArgumentException("La cantidad de pasajeros debe ser mayor a 0");
        }
        if (fechaPartida == null) {
            throw new IllegalArgumentException("La fecha de partida no puede ser nula");
        }
        if (esIdaVuelta && (fechaRetorno == null || !fechaRetorno.isAfter(fechaPartida))) {
            throw new IllegalArgumentException("La fecha de retorno debe ser posterior a la fecha de partida");
        }

        // Consultar el destino en la base de datos
        Destino destinoEncontrado = destinoRepositorio.findByCiudad(destino)
                .orElseThrow(() -> new IllegalArgumentException("Destino no encontrado en la base de datos: " + destino));

        // Determinar si el vuelo es internacional o nacional según el destino
        boolean esInternacional = destinoEncontrado.isEsInternacional();
        System.out.println("Destino: " + destino);
        System.out.println("Es internacional: " + esInternacional);

        // Obtener todas las aeronaves
        List<Aeronave> todasLasAeronaves = aeronaveRepositorio.findAll();

        // Filtrar aeronaves por tipo de vuelo, clase y capacidad
        List<Aeronave> aeronavesDisponibles = todasLasAeronaves.stream()
                .filter(aeronave -> aeronave.isEsInternacional() == esInternacional) // Filtrar por tipo de vuelo
                .filter(aeronave -> tieneCapacidadSuficiente(aeronave, clase, cantidadPasajeros)) // Filtrar por capacidad
                .collect(Collectors.toList());

        // Imprimir detalles de la fecha de partida y retorno si es un vuelo ida y vuelta
        System.out.println("Fecha de partida: " + fechaPartida);
        if (esIdaVuelta) {
            System.out.println("Fecha de retorno: " + fechaRetorno);
        }

        System.out.println("Aeronaves disponibles: " + aeronavesDisponibles);
        return aeronavesDisponibles;
    }



    public List<Silla> obtenerSillasPorCodigoAeronave(String codigoAeronave) {
        Aeronave aeronave = aeronaveRepositorio.findById(codigoAeronave).orElse(null);
        if (aeronave != null) {
            return aeronave.getListaSillas(); // Devuelve la lista de sillas embebidas en la aeronave
        }
        return Collections.emptyList(); // Devuelve una lista vacía si no se encuentra la aeronave
    }


    private boolean tieneCapacidadSuficiente(Aeronave aeronave, Clase clase, int cantidadPasajeros) {
        return switch (clase) {
            case ECONOMICA -> aeronave.getCapacidadClaseEconomica() >= cantidadPasajeros;
            case EJECUTIVA -> aeronave.getCapacidadClaseEjecutiva() >= cantidadPasajeros;
        };
    }

    private boolean verificarSiEsInternacional(String destino) {
        // Lista de destinos internacionales y nacionales
        List<String> destinosInternacionales = Arrays.asList("Buenos Aires", "Los Ángeles", "Bogotá", "Panamá");
        List<String> destinosNacionales = Arrays.asList("Cancún", "Monterrey");

        // Verificar si el destino es nacional o internacional
        if (destinosNacionales.contains(destino)) {
            return false; // Nacional
        } else if (destinosInternacionales.contains(destino)) {
            return true; // Internacional
        } else {
            throw new IllegalArgumentException("Destino no válido: " + destino);
        }
    }

    public List<Aeronave> obtenerTodasLasAeronaves() {
        return aeronaveRepositorio.findAll();
    }

    public Optional<Aeronave> obtenerAeronavePorId(Long id) {
        return aeronaveRepositorio.findById(String.valueOf(id));
    }
}
