package co.edu.uniquindio.airline.repositorios;

import co.edu.uniquindio.airline.modelo.Tiquete;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TiqueteRepositorio extends MongoRepository<Tiquete, String> {
}
