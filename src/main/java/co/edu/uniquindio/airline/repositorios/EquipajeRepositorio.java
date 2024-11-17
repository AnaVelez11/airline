package co.edu.uniquindio.airline.repositorios;

import co.edu.uniquindio.airline.modelo.Equipaje;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EquipajeRepositorio extends MongoRepository<Equipaje, String> {

    List<Equipaje> findByClienteCedula(String cedula); // Buscar equipaje por cliente

}
