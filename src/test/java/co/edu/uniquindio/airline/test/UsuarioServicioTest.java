package co.edu.uniquindio.airline.test;

import co.edu.uniquindio.airline.dto.CrearUsuarioDTO;
import co.edu.uniquindio.airline.dto.LoginDTO;
import co.edu.uniquindio.airline.dto.TokenDTO;
import co.edu.uniquindio.airline.servicios.interfaces.UsuarioServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UsuarioServicioTest {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Test
    public void crearCuentaTest() {

        // Crear un DTO con los datos para crear un nuevo cliente
        CrearUsuarioDTO crearUsuarioDTO = new CrearUsuarioDTO(
                "Ana",
                "Cleta",
                "anarami55@email.com",
                "ane123"
        );


        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow(() -> {
            // Se crea la cuenta y se imprime el id
            String id = usuarioServicio.crearUsuario(crearUsuarioDTO);
            // Se espera que el id no sea nulo
            assertNotNull(id);
        });

    }

    @Test
    public void crearCuentaRepetidaTest() {

        // Crear un DTO con los datos para crear un nuevo cliente
        CrearUsuarioDTO crearUsuarioDTO = new CrearUsuarioDTO(
                "Ana",
                "Velez",
                "anarami55@email.com",
                "ane123"
        );

        // Se espera que  se lance una excepción
        assertThrows(Exception.class, () -> {
            usuarioServicio.crearUsuario(crearUsuarioDTO);
        });

    }

    @Test
    public void iniciarSesionTest() {
        LoginDTO loginDTO = new LoginDTO(
                "anarami55@email.com",
                "ane123"

        );

        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow(() -> {
            // Llamada al método para iniciar sesión
            TokenDTO token = usuarioServicio.iniciarSesion(loginDTO);
            // Se espera que el token no sea nulo
            assertNotNull(token);
            // Imprimir el token en la consola
            System.out.println("Token generado: " + token.token());
        });
    }
}