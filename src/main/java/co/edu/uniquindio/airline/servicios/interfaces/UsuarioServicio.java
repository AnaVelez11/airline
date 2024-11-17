package co.edu.uniquindio.airline.servicios.interfaces;

import co.edu.uniquindio.airline.dto.CrearUsuarioDTO;
import co.edu.uniquindio.airline.dto.LoginDTO;
import co.edu.uniquindio.airline.dto.TokenDTO;

public interface UsuarioServicio {

    String crearUsuario(CrearUsuarioDTO usuario) throws Exception;

    TokenDTO iniciarSesion(LoginDTO loginDTO) throws Exception;
}
