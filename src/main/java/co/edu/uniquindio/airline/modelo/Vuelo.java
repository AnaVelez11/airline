package co.edu.uniquindio.airline.modelo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Document("vuelos")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Vuelo {

    @Id
    private String codigo;

    private String codigoVuelo;
    private LocalTime horaSalida;
    private Duration duracion;
    private String origen;
    private String destino;

    private List<Tripulante> listaTripulantes = new ArrayList<>();
    private List<Equipaje> listaEquipajes = new ArrayList<>();



}
