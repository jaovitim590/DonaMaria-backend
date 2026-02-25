package com.donaMaria_.demo.Dtos;

import com.donaMaria_.demo.models.Categories;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ResProductDto(
        @NotNull
        Long id,

        @NotBlank
        String name,

        @NotBlank
        String description,

        @NotNull
        @DecimalMin(value = "0.00", message = "O preço deve ser no mínimo 0.00")
        BigDecimal price,

        @NotBlank
        Categories category,

        @NotNull
        boolean available,

        @NotNull
        boolean featured
) {
}
