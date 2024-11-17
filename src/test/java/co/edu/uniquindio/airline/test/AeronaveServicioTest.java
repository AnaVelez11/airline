package co.edu.uniquindio.airline.test;

import co.edu.uniquindio.airline.modelo.Aeronave;
import co.edu.uniquindio.airline.modelo.Clase;
import co.edu.uniquindio.airline.repositorios.AeronaveRepositorio;
import co.edu.uniquindio.airline.repositorios.DestinoRepositorio;
import co.edu.uniquindio.airline.servicios.interfaces.AeronaveServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@SpringBootTest
public class AeronaveServicioTest {

    @Autowired
    private AeronaveServicio aeronaveServicio;

    @Autowired
    private AeronaveRepositorio aeronaveRepositorio;

    @Autowired
    private DestinoRepositorio destinoRepositorio;

    @Test
    public void obtenerAeronavesDisponiblesInternacionalesTest(){

        String origen = "CDMX";
        String destino = "Bogotá";
        Clase clase = Clase.ECONOMICA;
        int cantidadPasajeros = 30;
        boolean esIdaVuelta = true;
        // Definir las fechas de partida y retorno
        LocalDate fechaPartida = LocalDate.of(2024, 12, 1);
        LocalDate fechaRetorno = esIdaVuelta ? LocalDate.of(2024, 12, 10) : null;

        // Llamar al método a probar
        List<Aeronave> resultado = aeronaveServicio.obtenerAeronavesDisponiblesPorRuta(origen, destino, clase, cantidadPasajeros, esIdaVuelta, fechaPartida, fechaRetorno);

        // Imprimir solo atributos relevantes
        System.out.println("Aeronaves disponibles:");
        resultado.forEach(aeronave -> {
            System.out.println("Aeronave: " + aeronave.getCodigo() + ", Capacidad: " + aeronave.getCapacidadPasajeros());

            // Imprimir detalles de los vuelos asociados
            aeronave.getListaVuelos().forEach(vuelo -> {
                System.out.println("  Vuelo: " + vuelo.getCodigoVuelo());
                System.out.println("  Hora de salida: " + vuelo.getHoraSalida());
                System.out.println("  Duración: " + vuelo.getDuracion().toHours() + " horas y " + vuelo.getDuracion().toMinutesPart() + " minutos");
            });


        });

        // Verificaciones
        assertThat(resultado).hasSize(4);
        assertThat(resultado).isNotEmpty();


    }
    @Test
    public void obtenerAeronavesDisponiblesNacionalesTest(){

        String origen = "CDMX";
        String destino = "Monterrey";
        Clase clase = Clase.EJECUTIVA;
        int cantidadPasajeros = 5;
        boolean esIdaVuelta = false;
        // Definir las fechas de partida y retorno
        LocalDate fechaPartida = LocalDate.of(2024, 12, 1);
        LocalDate fechaRetorno = esIdaVuelta ? LocalDate.of(2024, 12, 10) : null;


        // Llamar al método a probar
        List<Aeronave> resultado = aeronaveServicio.obtenerAeronavesDisponiblesPorRuta(origen, destino, clase, cantidadPasajeros, esIdaVuelta, fechaPartida, fechaRetorno);

        // Imprimir solo atributos relevantes
        System.out.println("Aeronaves disponibles:");
        resultado.forEach(aeronave -> {
            System.out.println("Aeronave: " + aeronave.getCodigo() + ", Capacidad: " + aeronave.getCapacidadPasajeros());

            // Imprimir detalles de los vuelos asociados
            aeronave.getListaVuelos().forEach(vuelo -> {
                System.out.println("  Vuelo: " + vuelo.getCodigoVuelo());
                System.out.println("  Hora de salida: " + vuelo.getHoraSalida());
                System.out.println("  Duración: " + vuelo.getDuracion().toHours() + " horas y " + vuelo.getDuracion().toMinutesPart() + " minutos");
            });
        });

        // Verificaciones
        assertThat(resultado).hasSize(2);
        assertThat(resultado).isNotEmpty();



    }

}


