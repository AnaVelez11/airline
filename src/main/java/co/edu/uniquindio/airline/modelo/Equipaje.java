package co.edu.uniquindio.airline.modelo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("equipaje")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Equipaje {

    @Id
    private String codigo;

    private float peso;
    private String tipo;
    private String dimensiones;
    private float costoAdicional;

    private Vuelo vuelo;
    private Cliente cliente;

}
