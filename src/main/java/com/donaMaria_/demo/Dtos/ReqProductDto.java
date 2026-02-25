package com.donaMaria_.demo.Dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

public record ReqProductDto(
        @NotBlank
        String name,

        @NotBlank
        String description,

        @NotBlank
        @DecimalMin(value = "0.00", message = "O preço deve ser no mínimo 0.00")
        BigDecimal price,

        @NotBlank
        @Pattern(regexp = "TODOS|LANCHES|PIZZAS|BEBIDAS|SOBREMESAS")
        String category

) {
}
