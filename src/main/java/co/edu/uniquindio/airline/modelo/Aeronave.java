package co.edu.uniquindio.airline.modelo;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Document("aeronaves")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Aeronave {

    @Id
    private String codigo;

    private TipoAeronave tipo;
    private int capacidadPasajeros;
    private int capacidadClaseEjecutiva;
    private int capacidadClaseEconomica;
    private int capacidadCarga;
    private boolean esInternacional;
    private String numeracionEjecutiva;
    private String numeracionEconomica;

    private Aerolinea aerolinea;

    private List<Vuelo> mg = new ArrayList<>();
    private List<Silla> listaSillas = new ArrayList<>();

    @Override
    public String toString() {
        return "Aeronave{" +
                "codigo='" + codigo + '\'' +
                ", capacidadPasajeros=" + capacidadPasajeros +
                ", capacidadClaseEjecutiva=" + capacidadClaseEjecutiva +
                ", capacidadClaseEconomica=" + capacidadClaseEconomica +
                ", esInternacional=" + esInternacional +
                '}';
    }







}
