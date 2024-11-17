package co.edu.uniquindio.airline.dto;

import co.edu.uniquindio.airline.modelo.Cliente;
import co.edu.uniquindio.airline.modelo.Equipaje;
import co.edu.uniquindio.airline.modelo.Vuelo;

public record EquipajeDTO(
        float peso,
        String tipo,
        String dimensiones,
        float costoAdicional,
        Vuelo vuelo,
        String cedulaCliente

) {
    // Método para convertir el DTO a la entidad Equipaje
    public Equipaje toEntity(Cliente cliente) {
        Equipaje equipaje = new Equipaje();
        equipaje.setPeso(this.peso);
        equipaje.setTipo(this.tipo);
        equipaje.setDimensiones(this.dimensiones);
        equipaje.setCostoAdicional(this.costoAdicional);
        equipaje.setVuelo(this.vuelo);
        equipaje.setCliente(cliente); // Asociar el cliente al equipaje
        return equipaje;
    }
}
