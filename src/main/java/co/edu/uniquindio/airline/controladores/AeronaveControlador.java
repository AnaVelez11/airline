package co.edu.uniquindio.airline.controladores;

import co.edu.uniquindio.airline.modelo.Aeronave;
import co.edu.uniquindio.airline.modelo.Clase;
import co.edu.uniquindio.airline.modelo.Silla;
import co.edu.uniquindio.airline.servicios.interfaces.AeronaveServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/aeronaves")
public class AeronaveControlador {

    private final AeronaveServicio aeronaveServicio;

    // Endpoint para agregar una nueva aeronave
    @PostMapping("/agregar")
    public ResponseEntity<Aeronave> agregarAeronave(@RequestBody Aeronave aeronave) {
        try {
            Aeronave nuevaAeronave = aeronaveServicio.agregarAeronave(aeronave);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaAeronave);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Endpoint para obtener todas las aeronaves disponibles por ruta
    @GetMapping("/disponibles")
    public ResponseEntity<List<Aeronave>> obtenerAeronavesDisponibles(
            @RequestParam String origen,
            @RequestParam String destino,
            @RequestParam Clase clase,
            @RequestParam int cantidadPasajeros,
            @RequestParam boolean esIdaVuelta,
            @RequestParam LocalDate fechaPartida,
            @RequestParam LocalDate fechaRetorno) {
        try {
            List<Aeronave> aeronaves = aeronaveServicio.obtenerAeronavesDisponiblesPorRuta(origen, destino, clase, cantidadPasajeros, esIdaVuelta, fechaPartida, fechaRetorno);
            return ResponseEntity.ok(aeronaves);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Endpoint para obtener sillas por código de aeronave
    @GetMapping("/{codigoAeronave}/sillas")
    public ResponseEntity<List<Silla>> obtenerSillasPorCodigoAeronave(@PathVariable String codigoAeronave) {
        List<Silla> sillas = aeronaveServicio.obtenerSillasPorCodigoAeronave(codigoAeronave);
        if (sillas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(sillas);
    }

    // Endpoint para obtener todas las aeronaves
    @GetMapping("/listar")
    public ResponseEntity<List<Aeronave>> obtenerTodasLasAeronaves() {
        List<Aeronave> aeronaves = aeronaveServicio.obtenerTodasLasAeronaves();
        return ResponseEntity.ok(aeronaves);
    }

    // Endpoint para obtener una aeronave por ID
    @GetMapping("/{id}")
    public ResponseEntity<Aeronave> obtenerAeronavePorId(@PathVariable Long id) {
        Optional<Aeronave> aeronave = aeronaveServicio.obtenerAeronavePorId(id);
        return aeronave.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
}
