package com.donaMaria_.demo.Dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UpdateUserDto(
        @NotBlank
        Long id,

        String name,

        @Email(message = "Formato de email inválido")
        String email,

        String password
) {
}
