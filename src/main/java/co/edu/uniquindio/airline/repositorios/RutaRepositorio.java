package co.edu.uniquindio.airline.repositorios;

import co.edu.uniquindio.airline.modelo.Ruta;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RutaRepositorio extends MongoRepository<Ruta, String> {
}
