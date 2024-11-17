package co.edu.uniquindio.airline.repositorios;

import co.edu.uniquindio.airline.modelo.Vuelo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VueloRepositorio extends MongoRepository<Vuelo, String> {
}
