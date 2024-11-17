package co.edu.uniquindio.airline.repositorios;

import co.edu.uniquindio.airline.modelo.Tripulante;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TripulanteRepositorio extends MongoRepository<Tripulante, String> {
}
