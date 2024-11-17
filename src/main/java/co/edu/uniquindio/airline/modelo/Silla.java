package co.edu.uniquindio.airline.modelo;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Silla {

    private String codigo;
    private int fila;
    private String columna;
    private String clase;
    private TipoAeronave tipoAeronave;
    private boolean reservada;
}
