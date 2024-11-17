package co.edu.uniquindio.airline.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record LoginDTO(
        @NotBlank @Length(max = 50) @Email String email,
        @NotBlank @Length(min = 6, max = 15)String password
) {
}
