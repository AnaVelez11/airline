package co.edu.uniquindio.airline.repositorios;

import co.edu.uniquindio.airline.modelo.Destino;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DestinoRepositorio extends MongoRepository<Destino, String> {
    Optional<Destino> findByCiudad(String ciudad);


}
