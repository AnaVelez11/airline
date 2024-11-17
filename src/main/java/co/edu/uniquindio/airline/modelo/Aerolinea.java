package co.edu.uniquindio.airline.modelo;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import java.util.List;

@Document("aerolinea")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Aerolinea {

    @Id
    private String codigo;

    private String nombre;
    private List<Aeronave> listaAeronaves;
    private List<Tripulante> listaTripulantes;
    private List<Ruta> listaRutas;


}
