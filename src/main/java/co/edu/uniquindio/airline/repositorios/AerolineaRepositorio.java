package co.edu.uniquindio.airline.repositorios;

import co.edu.uniquindio.airline.modelo.Aerolinea;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AerolineaRepositorio extends MongoRepository<Aerolinea, String> {
}
