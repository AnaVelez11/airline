package co.edu.uniquindio.airline.servicios.interfaces;

import co.edu.uniquindio.airline.modelo.CarroEmbarque;
import co.edu.uniquindio.airline.modelo.Equipaje;

import java.util.List;

public interface CarroEmbarqueServicio {

    /**
     * Registrar la llegada de un carro de embarque.
     */
    void registrarLlegadaCarro(CarroEmbarque carro);

    /**
     * Dar salida al carro de embarque que ocupa la primera posición.
     */
    CarroEmbarque darSalidaCarro();

    /**
     * Retirar un carro específico de la fila.
     */
    CarroEmbarque retirarCarro(int numeroCarro);

    /**
     * Asignar equipaje a un carro de embarque.
     */
    void asignarEquipajeACarro(CarroEmbarque carro, List<Equipaje> equipajes);
}
