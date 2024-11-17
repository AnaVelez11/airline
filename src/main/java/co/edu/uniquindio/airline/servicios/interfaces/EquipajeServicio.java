package co.edu.uniquindio.airline.servicios.interfaces;

import co.edu.uniquindio.airline.modelo.Clase;
import co.edu.uniquindio.airline.modelo.Cliente;
import co.edu.uniquindio.airline.modelo.Equipaje;
import co.edu.uniquindio.airline.modelo.Tiquete;

import java.util.List;
import java.util.Set;

public interface EquipajeServicio {

    List<Equipaje> obtenerEquipajePorCliente(String clienteId);

    Equipaje registrarEquipaje(Equipaje equipaje, Clase clase, boolean esNacional, boolean esEquipajeDeMano, String cedula);

    float registrarMascota(String nombreMascota, float pesoMascota, String cedulaCliente);

}
