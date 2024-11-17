package co.edu.uniquindio.airline.test;

import co.edu.uniquindio.airline.modelo.Clase;
import co.edu.uniquindio.airline.modelo.Cliente;
import co.edu.uniquindio.airline.modelo.Equipaje;
import co.edu.uniquindio.airline.repositorios.ClienteRepositorio;
import co.edu.uniquindio.airline.servicios.interfaces.EquipajeServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;


@SpringBootTest
public class EquipajeServicioTest {

    @Autowired
    private EquipajeServicio equipajeServicio;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Test
    public void testRegistrarEquipaje() {
        String cedulaCliente = "123"; // Usa la cédula del cliente que ya existe en la base de datos

        // Verificar que el cliente existe en la base de datos
        Cliente cliente = clienteRepositorio.findByCedula(cedulaCliente)
                .orElseThrow(() -> new IllegalArgumentException("El cliente con la cédula " + cedulaCliente + " no existe en la base de datos."));

        // Crear un equipaje y asociarlo al cliente
        Equipaje equipaje = new Equipaje();
        equipaje.setCodigo("E3");
        equipaje.setPeso(23.0f);
        equipaje.setTipo("economica");
        equipaje.setDimensiones("55,45,35");

        Clase clase = Clase.ECONOMICA;
        boolean esNacional = true;
        boolean esEquipajeDeMano = false;

        Equipaje resultado = equipajeServicio.registrarEquipaje(equipaje, clase, esNacional, esEquipajeDeMano, cedulaCliente);

        Assertions.assertNotNull(resultado, "El equipaje registrado no debería ser nulo");
        Assertions.assertEquals("E3", resultado.getCodigo(), "El código del equipaje debería coincidir");
        Assertions.assertTrue(resultado.getCostoAdicional() >= 0, "El costo adicional debería ser válido");

        // Verificar que el cliente tiene el equipaje en su lista
        Cliente clienteActualizado = clienteRepositorio.findByCedula(cedulaCliente).orElseThrow();
        Assertions.assertTrue(clienteActualizado.getListaEquipajes().contains(equipaje), "El cliente debería tener el equipaje en su lista");
    }

    @Test
    public void testRegistrarMascota() {
        String nombreMascota = "Odi";
        float pesoMascota = 10.0f; // Peso superior a 9 kg para verificar costo adicional
        String cedulaCliente = "123"; // Usa la cédula del cliente que ya existe en la base de datos
        float costo = equipajeServicio.registrarMascota(nombreMascota,pesoMascota, cedulaCliente);

        Assertions.assertTrue(costo > 48.0f, "El costo de la mascota debería incluir el cargo adicional");

        // Verificar que la mascota se haya registrado en el cliente
        Cliente clienteActualizado = clienteRepositorio.findByCedula(cedulaCliente)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado después de registrar la mascota"));

        Assertions.assertFalse(clienteActualizado.getListaMascotas().isEmpty(), "La lista de mascotas del cliente no debería estar vacía");
        Assertions.assertEquals(1, clienteActualizado.getListaMascotas().size(), "La lista de mascotas debería tener 1 elemento");
        Assertions.assertEquals(pesoMascota, clienteActualizado.getListaMascotas().get(0).getPeso(), "El peso de la mascota debería coincidir");
        Assertions.assertEquals(costo, clienteActualizado.getListaMascotas().get(0).getCosto(), "El costo de la mascota debería coincidir");
    }

    @Test
    public void testObtenerEquipajePorCliente() {
        String cedulaCliente = "123"; // Usa la cédula del cliente que ya existe en la base de datos

        // Verificar que el cliente existe en la base de datos
        Cliente cliente = clienteRepositorio.findByCedula(cedulaCliente)
                .orElseThrow(() -> new IllegalArgumentException("El cliente con la cédula " + cedulaCliente + " no existe en la base de datos."));


        List<Equipaje> resultado = equipajeServicio.obtenerEquipajePorCliente(cedulaCliente);

        Assertions.assertFalse(resultado.isEmpty(), "La lista de equipaje no debería estar vacía");
        Assertions.assertEquals(1, resultado.size(), "El número de elementos devueltos debería ser 1");
        Assertions.assertEquals("E3", resultado.get(0).getCodigo(), "El código del primer equipaje debería coincidir");
    }




}
