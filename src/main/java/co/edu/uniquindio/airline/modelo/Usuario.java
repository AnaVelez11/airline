package co.edu.uniquindio.airline.modelo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("usuarios")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

    @Id
    private String codigo;

    private String name;
    private String lastname;
    private String email;
    private String password;
    private Rol rol;

}

