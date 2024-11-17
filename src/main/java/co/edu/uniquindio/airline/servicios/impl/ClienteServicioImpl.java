package co.edu.uniquindio.airline.servicios.impl;

import co.edu.uniquindio.airline.dto.*;
import co.edu.uniquindio.airline.modelo.Cliente;
import co.edu.uniquindio.airline.repositorios.ClienteRepositorio;
import co.edu.uniquindio.airline.servicios.interfaces.ClienteServicio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ClienteServicioImpl implements ClienteServicio {

    private final ClienteRepositorio clienteRepositorio;

    public ClienteServicioImpl(ClienteRepositorio clienteRepositorio){
        this.clienteRepositorio = clienteRepositorio;

    }

    @Override
    public String crearCliente(CrearClienteDTO clienteDTO) throws Exception {

        // 1. Verificar si el cliente ya existe por email
        if (clienteRepositorio.findByEmail(clienteDTO.email()).isPresent()) {
            throw new Exception("Ya existe un cliente registrado con el email: " + clienteDTO.email());
        }
        if(clienteRepositorio.findByCedula(clienteDTO.cedula()).isPresent()){
            throw new Exception("Ya existe un cliente registrado con la cédula: " + clienteDTO.cedula());
        }

        // 2. Crear una instancia de Cliente y asignar los valores desde el DTO
        Cliente cliente = new Cliente();
        cliente.setCedula(clienteDTO.cedula());
        cliente.setNombre(clienteDTO.nombre());
        cliente.setApellido(clienteDTO.apellido());
        cliente.setDireccion(clienteDTO.direccion());
        cliente.setEmail(clienteDTO.email());
        cliente.setNacimiento(clienteDTO.nacimiento());

        Cliente clienteGuardado = clienteRepositorio.save(cliente);

        return "Cliente creado con ID: " + clienteGuardado.getCodigo();
    }


}
