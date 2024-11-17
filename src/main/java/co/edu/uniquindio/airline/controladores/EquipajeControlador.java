package co.edu.uniquindio.airline.controladores;

import co.edu.uniquindio.airline.dto.EquipajeDTO;
import co.edu.uniquindio.airline.modelo.Clase;
import co.edu.uniquindio.airline.modelo.Cliente;
import co.edu.uniquindio.airline.modelo.Equipaje;
import co.edu.uniquindio.airline.repositorios.ClienteRepositorio;
import co.edu.uniquindio.airline.servicios.interfaces.EquipajeServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/equipajes")
public class EquipajeControlador {

    private final EquipajeServicio equipajeServicio;
    private final ClienteRepositorio clienteRepositorio;

    // Endpoint para registrar un equipaje
    @PostMapping("/registrar")
    public ResponseEntity<Equipaje> registrarEquipaje(
            @RequestBody EquipajeDTO equipajeDTO,
            @RequestParam Clase clase,
            @RequestParam boolean esNacional,
            @RequestParam boolean esEquipajeDeMano) {

        try {
            Cliente cliente = clienteRepositorio.findByCedula(equipajeDTO.cedulaCliente())
                    .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con la cédula: " + equipajeDTO.cedulaCliente()));

            Equipaje equipaje = equipajeDTO.toEntity(cliente);
            Equipaje resultado = equipajeServicio.registrarEquipaje(equipaje, clase, esNacional, esEquipajeDeMano, cliente.getCedula());
            return ResponseEntity.status(HttpStatus.CREATED).body(resultado);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Endpoint para registrar una mascota
    @PostMapping("/registrarMascota")
    public ResponseEntity<Float> registrarMascota(
            @RequestParam String nombreMascota,
            @RequestParam float pesoMascota,
            @RequestParam String cedulaCliente) {

        try {
            float costo = equipajeServicio.registrarMascota(nombreMascota,pesoMascota, cedulaCliente);
            return ResponseEntity.ok(costo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Endpoint para obtener equipaje por cliente
    @GetMapping("/cliente/{cedula}")
    public ResponseEntity<List<Equipaje>> obtenerEquipajePorCliente(@PathVariable String cedula) {
        List<Equipaje> equipajes = equipajeServicio.obtenerEquipajePorCliente(cedula);
        if (equipajes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(equipajes);
    }

}
