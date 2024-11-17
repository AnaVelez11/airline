package co.edu.uniquindio.airline.Inicializador;

import co.edu.uniquindio.airline.modelo.*;
import co.edu.uniquindio.airline.repositorios.AeronaveRepositorio;
import co.edu.uniquindio.airline.repositorios.DestinoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private AeronaveRepositorio aeronaveRepositorio;

    @Autowired
    private DestinoRepositorio destinoRepositorio;


    public DataInitializer(AeronaveRepositorio aeronaveRepositorio, DestinoRepositorio destinoRepositorio) {
        this.aeronaveRepositorio = aeronaveRepositorio;
        this.destinoRepositorio = destinoRepositorio;

    }

    private Map<String, LocalTime> horasSalidaPorDestino;
    private Map<String, Duration> duracionPorDestino;

    @Override
    public void run(String... args) throws Exception {
        inicializarMapeos();
        inicializarAeronaves();
        inicializarDestinos();
        //inicializarVuelos();
    }

    private void inicializarAeronaves() {

        // Inicializar A320
        for (int i = 1; i <= 2; i++) { // Dos aeronaves A320
            Aeronave a320 = new Aeronave();
            a320.setCodigo("A320-" + i); // Código único
            a320.setCapacidadPasajeros(150);
            a320.setCapacidadClaseEjecutiva(12);
            a320.setCapacidadClaseEconomica(138);
            a320.setCapacidadCarga(19000);
            a320.setEsInternacional(false); // Nacional
            a320.setNumeracionEjecutiva("AC DF " + i);
            a320.setNumeracionEconomica("ABC DEF " + i);
            a320.setTipo(TipoAeronave.AIRBUS_A320);

            // Verificar si ya existe
            if (!aeronaveRepositorio.existsById(a320.getCodigo())) {
                List<Silla> sillas = generarSillasParaAeronave(a320);
                a320.setListaSillas(sillas); // Agrega las sillas a la aeronave
                System.out.println("Guardando aeronave con " + sillas.size() + " sillas.");

                aeronaveRepositorio.save(a320);

            }
        }

        // Inicializar A330
        for (int i = 1; i <= 2; i++) { // Dos aeronaves A330
            Aeronave a330 = new Aeronave();
            a330.setCodigo("A330-" + i); // Código único
            a330.setCapacidadPasajeros(252);
            a330.setCapacidadClaseEjecutiva(30);
            a330.setCapacidadClaseEconomica(222);
            a330.setCapacidadCarga(52000);
            a330.setEsInternacional(true); // Internacional
            a330.setNumeracionEjecutiva("AC DF HK " + i);
            a330.setNumeracionEconomica("AC DEFG HK " + i);
            a330.setTipo(TipoAeronave.AIRBUS_A330);

            // Verificar si ya existe
            if (!aeronaveRepositorio.existsById(a330.getCodigo())) {
                List<Silla> sillas = generarSillasParaAeronave(a330);
                a330.setListaSillas(sillas); // Agrega las sillas a la aeronave
                System.out.println("Guardando aeronave con " + sillas.size() + " sillas.");
                aeronaveRepositorio.save(a330);
            }
        }

        // Inicializar Boeing 787
        for (int i = 1; i <= 2; i++) { // Dos aeronaves Boeing 787
            Aeronave boeing787 = new Aeronave();
            boeing787.setCodigo("B787-" + i); // Código único
            boeing787.setCapacidadPasajeros(250);
            boeing787.setCapacidadClaseEjecutiva(28);
            boeing787.setCapacidadClaseEconomica(222);
            boeing787.setCapacidadCarga(50000);
            boeing787.setEsInternacional(true); // Internacional
            boeing787.setNumeracionEjecutiva("AB DE LK " + i);
            boeing787.setNumeracionEconomica("ABC DEF JKL " + i);
            boeing787.setTipo(TipoAeronave.BOEING_787);

            // Verificar si ya existe
            if (!aeronaveRepositorio.existsById(boeing787.getCodigo())) {
                List<Silla> sillas = generarSillasParaAeronave(boeing787);
                boeing787.setListaSillas(sillas); // Agrega las sillas a la aeronave
                System.out.println("Guardando aeronave con " + sillas.size() + " sillas.");
                aeronaveRepositorio.save(boeing787);
            }
        }
    }


    private List<Silla> generarSillasParaAeronave(Aeronave aeronave) {
        List<Silla> sillas = new ArrayList<>();
        int totalEjecutiva = aeronave.getCapacidadClaseEjecutiva();
        int totalEconomica = aeronave.getCapacidadClaseEconomica();

        // Generar sillas de clase ejecutiva según el tipo de aeronave
        if (aeronave.getTipo() == TipoAeronave.AIRBUS_A320) {
            for (int fila = 1; fila <= (totalEjecutiva / 4); fila++) {
                for (String columna : new String[]{"A", "C", "D", "F"}) {
                    Silla silla = new Silla();
                    silla.setFila(fila);
                    silla.setColumna(columna);
                    silla.setClase("Ejecutiva");
                    silla.setCodigo("S-" + aeronave.getCodigo() + "-" + fila + columna);
                    silla.setReservada(false);
                    sillas.add(silla);
                }
            }
        } else if (aeronave.getTipo() == TipoAeronave.AIRBUS_A330) {
            for (int fila = 1; fila <= (totalEjecutiva / 6); fila++) {
                for (String columna : new String[]{"A", "C", "D", "F", "H", "K"}) {
                    Silla silla = new Silla();
                    silla.setFila(fila);
                    silla.setColumna(columna);
                    silla.setClase("Ejecutiva");
                    silla.setCodigo("S-" + aeronave.getCodigo() + "-" + fila + columna);
                    silla.setReservada(false);
                    sillas.add(silla);
                }
            }
        } else if (aeronave.getTipo() == TipoAeronave.BOEING_787) {
            for (int fila = 1; fila <= (totalEjecutiva / 4); fila++) {
                for (String columna : new String[]{"A", "B", "D", "E", "L", "K"}) {
                    Silla silla = new Silla();
                    silla.setFila(fila);
                    silla.setColumna(columna);
                    silla.setClase("Ejecutiva");
                    silla.setCodigo("S-" + aeronave.getCodigo() + "-" + fila + columna);
                    silla.setReservada(false);
                    sillas.add(silla);
                }
            }
        }

        // Generar sillas de clase económica según el tipo de aeronave
        if (aeronave.getTipo() == TipoAeronave.AIRBUS_A320) {
            for (int fila = 1; fila <= (totalEconomica / 6); fila++) {
                for (String columna : new String[]{"A", "B", "C", "D", "E", "F"}) {
                    Silla silla = new Silla();
                    silla.setFila(fila);
                    silla.setColumna(columna);
                    silla.setClase("Economica");
                    silla.setCodigo("S-" + aeronave.getCodigo() + "-" + fila + columna);
                    silla.setReservada(false);
                    sillas.add(silla);
                }
            }
        } else if (aeronave.getTipo() == TipoAeronave.AIRBUS_A330) {
            for (int fila = 1; fila <= (totalEconomica / 8); fila++) {
                for (String columna : new String[]{"A", "C", "D", "E", "F", "G", "H", "K"}) {
                    Silla silla = new Silla();
                    silla.setFila(fila);
                    silla.setColumna(columna);
                    silla.setClase("Economica");
                    silla.setCodigo("S-" + aeronave.getCodigo() + "-" + fila + columna);
                    silla.setReservada(false);
                    sillas.add(silla);
                }
            }
        } else if (aeronave.getTipo() == TipoAeronave.BOEING_787) {
            for (int fila = 1; fila <= (totalEconomica / 9); fila++) {
                for (String columna : new String[]{"A", "B", "C", "D", "E", "F", "J", "K", "L"}) {
                    Silla silla = new Silla();
                    silla.setFila(fila);
                    silla.setColumna(columna);
                    silla.setClase("Economica");
                    silla.setCodigo("S-" + aeronave.getCodigo() + "-" + fila + columna);
                    silla.setReservada(false);
                    sillas.add(silla);
                }
            }
        }
        System.out.println("Sillas generadas: " + sillas.size());
        return sillas;
    }

    private void inicializarDestinos() {

        // Verificar si los destinos ya existen en la base de datos
        if (destinoRepositorio.count() == 0) {
            List<Destino> destinos = Arrays.asList(
                    new Destino("Monterrey", false),
                    new Destino("Cancún", false),
                    new Destino("Buenos Aires", true),
                    new Destino("Los Angeles", true),
                    new Destino("Bogotá", true),
                    new Destino("Panamá", true)
            );
            destinoRepositorio.saveAll(destinos);
        }
    }
    private void inicializarMapeos(){

        horasSalidaPorDestino = new HashMap<>();
        duracionPorDestino = new HashMap<>();

        // Agregar horas de salida y duraciones para cada destino
        horasSalidaPorDestino.put("Monterrey", LocalTime.of(6, 0));
        duracionPorDestino.put("Monterrey", Duration.ofHours(2).plusMinutes(45));

        horasSalidaPorDestino.put("Cancún", LocalTime.of( 8, 0));
        duracionPorDestino.put("Cancún", Duration.ofHours(3).plusMinutes(12));

        horasSalidaPorDestino.put("Buenos Aires", LocalTime.of(23, 30));
        duracionPorDestino.put("Buenos Aires", Duration.ofHours(9).plusMinutes(5));

        horasSalidaPorDestino.put("Los Angeles", LocalTime.of( 9, 45));
        duracionPorDestino.put("Los Angeles", Duration.ofHours(3).plusMinutes(25));

        horasSalidaPorDestino.put("Bogotá", LocalTime.of(13, 30));
        duracionPorDestino.put("Bogotá", Duration.ofHours(3).plusMinutes(45));

        horasSalidaPorDestino.put("Panamá", LocalTime.of(14, 45));
        duracionPorDestino.put("Panamá", Duration.ofHours(2).plusMinutes(55));

    }

    /*
    // REVISAR INICIALIZADOR
    private void inicializarVuelos() {
        List<Aeronave> aeronaves = aeronaveRepositorio.findAll();

        if (aeronaves.isEmpty()) {
            System.out.println("No hay aeronaves en la base de datos.");
            return;
        }

        // Crear vuelos para cada aeronave
        for (Aeronave aeronave : aeronaves) {

            String destino = "Monterrey";

            // Obtener la hora de salida y duración para el destino
            LocalTime horaSalida = horasSalidaPorDestino.get(destino);
            Duration duracion = duracionPorDestino.get(destino);

            if (horaSalida != null && duracion != null) {
                Vuelo vuelo = new Vuelo();
                vuelo.setCodigo("V-" + aeronave.getCodigo());
                vuelo.setCodigoVuelo("VUELO-" + aeronave.getCodigo());
                vuelo.setHoraSalida(horaSalida);
                vuelo.setDuracion(duracion);
                vuelo.setOrigen("CDMX");
                vuelo.setDestino(destino);

                // Agregar el vuelo a la lista de vuelos de la aeronave
                aeronave.getListaVuelos().add(vuelo);

                // Guardar la aeronave con el vuelo asociado
                aeronaveRepositorio.save(aeronave);

                System.out.println("Vuelo " + vuelo.getCodigo() + " asociado a la aeronave " + aeronave.getCodigo());
            } else {
                System.out.println("No se encontraron datos de hora de salida o duración para el destino: " + destino);
            }
        }
    }

     */


}

