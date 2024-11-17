package co.edu.uniquindio.airline.test;

import co.edu.uniquindio.airline.dto.CrearClienteDTO;
import co.edu.uniquindio.airline.servicios.interfaces.ClienteServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ClienteServicioTest {

    @Autowired
    private ClienteServicio clienteServicio;

    @Test
    public void crearCuentaTest(){
        // Crear una fecha de nacimiento válida
        LocalDate fechaNacimiento = LocalDate.of(1990, 5, 15);

        // Crear un DTO con los datos para crear un nuevo cliente
        CrearClienteDTO crearClienteDTO = new CrearClienteDTO(
                "123",
                "Pepito",
                "perez",
                "crr 57-12 alameda",
                "pepito89@mail.com",
                fechaNacimiento
        );

        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow( () -> {
            // Se crea la cuenta y se imprime el id
            String id = clienteServicio.crearCliente(crearClienteDTO);
            // Se espera que el id no sea nulo
            assertNotNull(id);
        } );

    }

    @Test
    public void crearCuentaRepetidaTest(){
        // Crear una fecha de nacimiento válida
        LocalDate fechaNacimiento = LocalDate.of(1980, 2, 9);

        // Crear un DTO con los datos para crear un nuevo cliente
        CrearClienteDTO crearClienteDTO = new CrearClienteDTO(
                "123",
                "Pepita",
                "velez",
                "crr 12 centro",
                "pepitaV@mail.com",
                fechaNacimiento
        );

        // Se espera que  se lance una excepción
        assertThrows(Exception.class, () -> {
            clienteServicio.crearCliente(crearClienteDTO);
        });

    }
    @Test
    public void crearCuentaUsuarioExistenteTest(){
        // Crear una fecha de nacimiento válida
        LocalDate fechaNacimiento = LocalDate.of(1990, 8, 12);

        // Crear un DTO con los datos para crear un nuevo cliente
        CrearClienteDTO crearClienteDTO = new CrearClienteDTO(
                "233",
                "Ana",
                "Cleta",
                "crr 1a norte",
                "anarami55@email.com",
                fechaNacimiento
        );

        // Se espera que no se lance ninguna excepción
        assertDoesNotThrow( () -> {
            // Se crea la cuenta y se imprime el id
            String id = clienteServicio.crearCliente(crearClienteDTO);
            // Se espera que el id no sea nulo
            assertNotNull(id);
        } );

    }
    @Test
    public void crearCuentaCorreoExistenteTest(){
        // Crear una fecha de nacimiento válida
        LocalDate fechaNacimiento = LocalDate.of(1998, 5, 22);

        // Crear un DTO con los datos para crear un nuevo cliente
        CrearClienteDTO crearClienteDTO = new CrearClienteDTO(
                "147",
                "Ana",
                "Ramirez",
                "calle 9na 56-89",
                "anarami55@email.com",
                fechaNacimiento
        );

        // Se espera que  se lance una excepción
        assertThrows(Exception.class, () -> {
            clienteServicio.crearCliente(crearClienteDTO);
        });

    }
}
