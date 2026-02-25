package com.donaMaria_.demo.Dtos;

import jakarta.validation.constraints.*;


public record ReqUserDto(
        @NotBlank
        @Size(min = 4 , message = "Nome muito curto")
        String name,

        @NotBlank
        @Email(message = "Email inválido")
        String email,

        @NotBlank
        @Size(min = 5, message = "Senha muito curta")
        String password,

        @NotBlank
        @Pattern(regexp = "ADMIN|USER")
        String role

) {
}
