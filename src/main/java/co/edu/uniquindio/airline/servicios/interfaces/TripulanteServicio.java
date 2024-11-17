package co.edu.uniquindio.airline.servicios.interfaces;

import co.edu.uniquindio.airline.modelo.Aeronave;
import co.edu.uniquindio.airline.modelo.Tripulante;

import java.util.List;
import java.util.Optional;

public interface TripulanteServicio {

    /**
     * Crear un tripulante.
     */
    Tripulante crearTripulante(Tripulante tripulante);

    /**
     * Obtener todos los tripulantes.
     */
    List<Tripulante> obtenerTodosLosTripulantes();

    /**
     * Obtener un tripulante por su ID.
     */
    Optional<Tripulante> obtenerTripulantePorId(Long id);

    /**
     * Asignar un tripulante a una aeronave.
     */
    void asignarTripulacion(Aeronave aeronave, List<Tripulante> tripulantes);

    /**
     * Cargar la tripulación desde un archivo.
     */
    void cargarTripulantesDesdeArchivo(String rutaArchivo);
}
