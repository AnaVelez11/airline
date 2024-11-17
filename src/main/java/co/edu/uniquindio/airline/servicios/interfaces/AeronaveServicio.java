package co.edu.uniquindio.airline.servicios.interfaces;

import co.edu.uniquindio.airline.modelo.Aeronave;
import co.edu.uniquindio.airline.modelo.Clase;
import co.edu.uniquindio.airline.modelo.Silla;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AeronaveServicio {

    Aeronave agregarAeronave(Aeronave aeronave);

    List<Aeronave> obtenerAeronavesDisponiblesPorRuta(String origen, String destino, Clase clase, int cantidadPasajeros, boolean esIdaVuelta, LocalDate fechaPartida, LocalDate fechaRetorno);

    List<Silla> obtenerSillasPorCodigoAeronave(String codigoAeronave);

    List<Aeronave> obtenerTodasLasAeronaves();

    Optional<Aeronave> obtenerAeronavePorId(Long id);
}
