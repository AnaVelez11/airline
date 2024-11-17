package co.edu.uniquindio.airline.servicios.impl;


import co.edu.uniquindio.airline.config.JWTUtils;
import co.edu.uniquindio.airline.dto.CrearUsuarioDTO;
import co.edu.uniquindio.airline.dto.LoginDTO;
import co.edu.uniquindio.airline.dto.TokenDTO;
import co.edu.uniquindio.airline.modelo.Usuario;
import co.edu.uniquindio.airline.modelo.Rol;
import co.edu.uniquindio.airline.repositorios.UsuarioRepositorio;
import co.edu.uniquindio.airline.servicios.interfaces.UsuarioServicio;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class UsuarioServicioImpl implements UsuarioServicio {

    private final UsuarioRepositorio usuarioRepositorio;
    private final JWTUtils jwtUtils;


    public UsuarioServicioImpl(UsuarioRepositorio usuarioRepositorio, JWTUtils jwtUtils){
        this.usuarioRepositorio = usuarioRepositorio;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public String crearUsuario(CrearUsuarioDTO usuarioDTO) throws Exception {

        // 1. Verificar si el cliente ya existe por email
        if (usuarioRepositorio.findByEmail(usuarioDTO.email()).isPresent()) {
            throw new Exception("Ya existe un usuario registrado con el email: " + usuarioDTO.email());
        }

        // 2. Crear una instancia de Usuario y asignar los valores desde el DTO
        Usuario usuario = new Usuario();
        usuario.setName(usuarioDTO.name());
        usuario.setLastname(usuarioDTO.lastname());
        usuario.setEmail(usuarioDTO.email());
        usuario.setRol(Rol.CLIENTE); // Asignar el rol del usuario desde el DTO

        // 3. Codificar la contraseña y asignarla
        String passwordCodificada = encriptarPassword(usuarioDTO.password());
        usuario.setPassword(passwordCodificada);

        // 4. Guardar el usuario en la base de datos
        Usuario usuarioGuardado = usuarioRepositorio.save(usuario);

        // 5. Retornar un mensaje de confirmación o el ID del usuario creado
        return "Usuario creado con ID: " + usuarioGuardado.getCodigo() + " y rol: " + usuarioGuardado.getRol();
    }


    @Override
    public TokenDTO iniciarSesion(LoginDTO loginDTO) throws Exception {

        Usuario usuario = obtenerPorEmail(loginDTO.email());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if( !passwordEncoder.matches(loginDTO.password(), usuario.getPassword()) ) {
            throw new Exception("La contraseña es incorrecta");
        }

        Map<String, Object> map = construirClaims(usuario);
        return new TokenDTO(jwtUtils.generarToken(usuario.getEmail(), map) );
    }

    private String encriptarPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    public Usuario obtenerPorEmail(String email) throws Exception {
        return usuarioRepositorio.findByEmail(email)
                .orElseThrow(() -> new Exception("Usuario no encontrado con el email: " + email));
    }

    public Map<String, Object> construirClaims(Usuario usuario) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("nombre", usuario.getName());
        claims.put("email", usuario.getEmail());
        return claims;
    }

}
