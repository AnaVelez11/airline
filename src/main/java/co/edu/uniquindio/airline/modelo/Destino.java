package co.edu.uniquindio.airline.modelo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("destinos")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Destino {

    @Id
    private String codigo;

    private String ciudad;
    private boolean esInternacional;

    public Destino(String ciudad, boolean esInternacional) {
        this.ciudad = ciudad;
        this.esInternacional = esInternacional;
    }


}
