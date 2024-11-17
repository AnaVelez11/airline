package co.edu.uniquindio.airline.modelo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document("tiquetes")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Tiquete {

    @Id
    private String codigo;

    private float costo;
    private String clase;
    private LocalDate fechaCompra;
    private String metodoPago;

    private Vuelo vuelo;
    private Cliente cliente;



}
