package co.edu.uniquindio.airline.repositorios;

import co.edu.uniquindio.airline.modelo.Aerolinea;
import co.edu.uniquindio.airline.modelo.Aeronave;
import co.edu.uniquindio.airline.modelo.TipoAeronave;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AeronaveRepositorio extends MongoRepository<Aeronave, String> {
    @Query("{ 'esInternacional': ?0, 'capacidadClaseEconomica': { $gt: 0 }, 'capacidadClaseEjecutiva': { $gt: 0 } }")
    List<Aeronave> findAeronavesDisponibles(boolean esInternacional, String clase);




}
