package co.edu.uniquindio.airline.modelo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("factura")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Factura {

    @Id
    private String codigo;

    private String metodoPago; // tarjeta de crédito, débito, etc.
    private float costo; // Costo total de la factura

    private List<Tiquete> listaTiquetes; // Relación con los tiquetes comprados
    private Cliente cliente; // Relación con el cliente que realizó la compra
}
