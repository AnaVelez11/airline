package co.edu.uniquindio.airline.controladores;

import co.edu.uniquindio.airline.dto.CrearUsuarioDTO;
import co.edu.uniquindio.airline.dto.LoginDTO;
import co.edu.uniquindio.airline.dto.ResponseDTO;
import co.edu.uniquindio.airline.dto.TokenDTO;
import co.edu.uniquindio.airline.servicios.interfaces.UsuarioServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuarios")
public class UsuarioControlador {

    private final UsuarioServicio usuarioServicio;

    // Endpoint para crear un usuario
    @PostMapping("/crear-usuario")
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody CrearUsuarioDTO usuarioDTO) {
        try {
            String respuesta = usuarioServicio.crearUsuario(usuarioDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(respuesta));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }


    // Endpoint para iniciar sesión
    @PostMapping("/login")
    public ResponseEntity<TokenDTO> iniciarSesion(@Valid @RequestBody LoginDTO loginDTO) {
        try {
            TokenDTO tokenDTO = usuarioServicio.iniciarSesion(loginDTO);
            return ResponseEntity.ok(tokenDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}
