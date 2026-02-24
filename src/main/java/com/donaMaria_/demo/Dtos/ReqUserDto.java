package com.donaMaria_.demo.Dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


public record ReqUserDto(
        @NotBlank
        @Min(4)
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Min(5)
        String password,

        @NotBlank
        @Pattern(regexp = "ADMIN|USER")
        String role

) {
}
