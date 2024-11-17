package co.edu.uniquindio.airline.servicios.interfaces;

import co.edu.uniquindio.airline.modelo.Aeronave;
import co.edu.uniquindio.airline.modelo.Tripulante;
import co.edu.uniquindio.airline.modelo.Vuelo;

import java.util.List;
import java.util.Optional;

public interface VueloServicio {

    /**
     * Crear un vuelo.
     */
    Vuelo crearVuelo(Vuelo vuelo);

    /**
     * Obtener un vuelo por su ID.
     */
    Optional<Vuelo> obtenerVueloPorId(String vueloId);

    /**
     * Obtener todos los vuelos según los criterios de búsqueda.
     */
    List<Vuelo> buscarVuelos(String origen, String destino, String fecha);

    /**
     * Asignar tripulación a un vuelo.
     */
    void asignarTripulacionAVuelo(String vueloId, List<Tripulante> tripulantes);

    /**
     * Asignar una aeronave a un vuelo.
     */
    void asignarAeronaveAVuelo(String vueloId, Aeronave aeronave);
}
