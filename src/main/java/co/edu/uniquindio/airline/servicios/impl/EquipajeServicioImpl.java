package co.edu.uniquindio.airline.servicios.impl;

import co.edu.uniquindio.airline.modelo.Clase;
import co.edu.uniquindio.airline.modelo.Cliente;
import co.edu.uniquindio.airline.modelo.Equipaje;
import co.edu.uniquindio.airline.modelo.Mascota;
import co.edu.uniquindio.airline.repositorios.ClienteRepositorio;
import co.edu.uniquindio.airline.repositorios.EquipajeRepositorio;
import co.edu.uniquindio.airline.servicios.interfaces.EquipajeServicio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class EquipajeServicioImpl implements EquipajeServicio {

    private EquipajeRepositorio equipajeRepositorio;
    private ClienteRepositorio clienteRepositorio;

    private static final float PESO_MAX_ECONOMICO_NACIONAL = 24.0f;
    private static final float PESO_MAX_EJECUTIVA_NACIONAL = 34.0f;
    private static final float PESO_MAX_ECONOMICO_INTERNACIONAL = 24.0f;
    private static final float PESO_MAX_EJECUTIVA_INTERNACIONAL = 34.0f;
    private static final float DIMENSIONES_MAX = 170.0f;
    private static final float DIMENSIONES_MAX_MANO = 110.0f;
    private static final float TARIFA_SOBREPESO = 8.0f;
    private static final float IVA = 1.0675f;
    private static final float TARIFA_MASCOTA_BASE = 48.0f;
    private static final float TARIFA_MASCOTA_EXTRA = 2.0f;

    public EquipajeServicioImpl(EquipajeRepositorio equipajeRepositorio, ClienteRepositorio clienteRepositorio) {
        this.equipajeRepositorio = equipajeRepositorio;
        this.clienteRepositorio = clienteRepositorio;
    }

    @Override
    public Equipaje registrarEquipaje(Equipaje equipaje, Clase clase, boolean esNacional, boolean esEquipajeDeMano, String cedulaCliente) {
        // Buscar al cliente en la base de datos
        Cliente cliente = clienteRepositorio.findByCedula(cedulaCliente)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con la cédula: " + cedulaCliente));

        // Asignar el cliente al equipaje
        equipaje.setCliente(cliente);

        // Validaciones de equipaje
        if (esEquipajeDeMano) {
            if (!validarEquipajeMano(equipaje)) {
                throw new IllegalArgumentException("El equipaje de mano no cumple con las restricciones de dimensiones.");
            }
        } else {
            if (!validarEquipaje(equipaje, clase, esNacional)) {
                throw new IllegalArgumentException("El equipaje no cumple con las restricciones de peso y dimensiones.");
            }
        }

        float pesoMaximoPermitido = obtenerPesoMaximoPermitido(clase, esNacional);
        float costoAdicional = calcularCostoAdicional(equipaje, pesoMaximoPermitido);
        equipaje.setCostoAdicional(costoAdicional);

        // Guardar el equipaje en la base de datos y actualizar el cliente
        Equipaje equipajeGuardado = equipajeRepositorio.save(equipaje);
        cliente.getListaEquipajes().add(equipajeGuardado);
        clienteRepositorio.save(cliente);

        return equipajeGuardado;
    }


    private float obtenerPesoMaximoPermitido(Clase clase, boolean esNacional) {
        return switch (clase) {
            case ECONOMICA -> esNacional ? PESO_MAX_ECONOMICO_NACIONAL : PESO_MAX_ECONOMICO_INTERNACIONAL;
            case EJECUTIVA -> esNacional ? PESO_MAX_EJECUTIVA_NACIONAL : PESO_MAX_EJECUTIVA_INTERNACIONAL;
        };
    }

    public boolean validarEquipaje(Equipaje equipaje, Clase clase, boolean esNacional) {
        float sumaDimensiones = calcularSumaDimensiones(equipaje.getDimensiones());

        if (sumaDimensiones > DIMENSIONES_MAX) {
            throw new IllegalArgumentException("Las dimensiones del equipaje exceden el límite permitido de " + DIMENSIONES_MAX + " cm");
        }

        float pesoMaximoPermitido = obtenerPesoMaximoPermitido(clase, esNacional);
        if (equipaje.getPeso() > pesoMaximoPermitido) {
            throw new IllegalArgumentException("El peso del equipaje excede el límite permitido de " + pesoMaximoPermitido + " kg");
        }

        return true;
    }

    public boolean validarEquipajeMano(Equipaje equipaje) {
        float sumaDimensiones = calcularSumaDimensiones(equipaje.getDimensiones());

        if (sumaDimensiones > DIMENSIONES_MAX_MANO) {
            throw new IllegalArgumentException("Las dimensiones del equipaje de mano exceden el límite permitido de " + DIMENSIONES_MAX_MANO + " cm.");
        }

        return true;
    }

    private float calcularSumaDimensiones(String dimensiones) {
        String[] dimParts = dimensiones.split(",");
        if (dimParts.length != 3) {
            throw new IllegalArgumentException("Las dimensiones deben incluir alto, largo y ancho separados por comas");
        }
        float alto = Float.parseFloat(dimParts[0]);
        float largo = Float.parseFloat(dimParts[1]);
        float ancho = Float.parseFloat(dimParts[2]);
        return alto + largo + ancho;
    }


    public float calcularCostoAdicional(Equipaje equipaje, float pesoMaximoPermitido) {
        float sobrepeso = equipaje.getPeso() - pesoMaximoPermitido;
        return sobrepeso > 0 ? (TARIFA_SOBREPESO * sobrepeso) * IVA : 0;
    }

    @Override
    public float registrarMascota(String nombreMascota, float pesoMascota, String cedulaCliente) {
        // Buscar al cliente en la base de datos
        Cliente cliente = clienteRepositorio.findByCedula(cedulaCliente)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con la cédula: " + cedulaCliente));

        // Verificar el peso de la mascota
        if (pesoMascota < 3) {
            throw new IllegalArgumentException("La mascota debe pesar al menos 3 kg para ser registrada.");
        }

        // Calcular el costo de la mascota
        float costo = calcularCostoMascota(pesoMascota);

        // Crear una nueva mascota con nombre, peso y costo
        Mascota nuevaMascota = new Mascota(nombreMascota, pesoMascota, costo);
        cliente.getListaMascotas().add(nuevaMascota);

        // Guardar el cliente actualizado en la base de datos
        clienteRepositorio.save(cliente);

        return costo;
    }

    private float calcularCostoMascota(float pesoMascota) {
        return (pesoMascota <= 9) ? TARIFA_MASCOTA_BASE : TARIFA_MASCOTA_BASE + (pesoMascota - 9) * TARIFA_MASCOTA_EXTRA;
    }

    @Override
    public List<Equipaje> obtenerEquipajePorCliente(String cedula) {
        return equipajeRepositorio.findByClienteCedula(cedula);
    }
}
