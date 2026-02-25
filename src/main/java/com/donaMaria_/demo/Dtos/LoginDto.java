package com.donaMaria_.demo.Dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record LoginDto(
        @Email(message = "envie um email valido!")
        String email,

        @NotNull
        String password
) {
}
