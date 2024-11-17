package co.edu.uniquindio.airline;

import co.edu.uniquindio.airline.modelo.Cliente;
import co.edu.uniquindio.airline.servicios.interfaces.ClienteServicio;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Map;

@SpringBootApplication
public class ProyectoApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(ProyectoApplication.class, args);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Permite solicitudes desde http://localhost:4200 (o el puerto de tu frontend)
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")  // Cambia si tu frontend está en otro puerto
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
    }
}
