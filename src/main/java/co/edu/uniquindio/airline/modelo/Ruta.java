package co.edu.uniquindio.airline.modelo;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;


@Document("ruta")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Ruta {

    @Id
    private String codigo;

    private String origen;
    private String destino;
    private String duracion;
    private String horaSalida;


    private Aerolinea aerolinea;
    private List<Vuelo> listaVuelos = new ArrayList<>();
}
