package co.edu.uniquindio.airline.controladores;

import co.edu.uniquindio.airline.dto.CrearClienteDTO;
import co.edu.uniquindio.airline.servicios.interfaces.ClienteServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clientes")
public class ClienteControlador {

    private final ClienteServicio clienteServicio;

    // Endpoint para crear un cliente
    @PostMapping("/crear-clientes")
    public ResponseEntity<String> crearCliente(@RequestBody CrearClienteDTO clienteDTO) {
        try {
            String respuesta = clienteServicio.crearCliente(clienteDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }
}

