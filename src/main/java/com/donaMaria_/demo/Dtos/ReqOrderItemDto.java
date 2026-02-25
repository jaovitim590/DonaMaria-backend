package com.donaMaria_.demo.Dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

public record ReqOrderItemDto(
        @NotNull
        Long productId,

        @NotNull
        @Min(value = 1, message = "A quantidade deve ser no mínimo 1")
        Integer quantity
) {}