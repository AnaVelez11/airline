package co.edu.uniquindio.airline.servicios.interfaces;

import co.edu.uniquindio.airline.modelo.Aeronave;
import co.edu.uniquindio.airline.modelo.Cliente;
import co.edu.uniquindio.airline.modelo.Silla;
import co.edu.uniquindio.airline.modelo.TipoAeronave;

import java.util.List;

public interface SillaServicio {

    Silla crearSilla(int fila, String columna, String clase, TipoAeronave tipo, Aeronave aeronave);


    List<Silla> generarSillasParaAeronave(TipoAeronave tipoAeronave, Aeronave aeronave);

    List<Silla> obtenerSillasPorAeronave(String aeronaveId);

    List<Silla> reservarSillas(int cantidad, String aeronaveId);

}
