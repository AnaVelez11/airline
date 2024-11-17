package co.edu.uniquindio.airline.servicios.interfaces;

import co.edu.uniquindio.airline.modelo.Cliente;
import co.edu.uniquindio.airline.modelo.Ruta;
import co.edu.uniquindio.airline.modelo.Tiquete;
import co.edu.uniquindio.airline.modelo.Vuelo;

import java.util.List;

public interface TiqueteServicio {

    /**
     * Comprar tiquete para un cliente y asignar asiento.
     */
    Tiquete comprarTiquete(Tiquete tiquete, Cliente cliente, Ruta ruta, String clase, boolean idaYVuelta);

    /**
     * Mostrar todas las opciones de vuelo disponibles según los criterios.
     */
    List<Vuelo> mostrarOpcionesDeVuelo(String origen, String destino, String clase, boolean idaYVuelta);

    /**
     * Calcular el costo del tiquete según el tipo de vuelo.
     */
    double calcularCostoTiquete(Ruta ruta, String clase);
}
