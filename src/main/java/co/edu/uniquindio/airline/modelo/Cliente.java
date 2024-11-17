package co.edu.uniquindio.airline.modelo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Document("clientes")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente {

    @Id
    private String codigo;

    private String cedula;
    private String nombre;
    private String apellido;
    private String direccion;
    private String email;
    private LocalDate nacimiento;

    @DBRef
    private List<Equipaje> listaEquipajes = new ArrayList<>();

    private List<Mascota> listaMascotas = new ArrayList<>();

}
