package com.donaMaria_.demo.Dtos;

import com.donaMaria_.demo.models.Categories;
import jakarta.validation.constraints.DecimalMin;

import java.math.BigDecimal;

public record UpdateProductDto(
        String name,

        String description,

        @DecimalMin(value = "0.00", message = "O preço deve ser no mínimo 0.00")
        BigDecimal price,

        Categories category,

        Boolean available,

        Boolean featured
) {
}
