package com.donaMaria_.demo.Dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ReqOrderDto(
        @NotNull
        Long userId,

        @NotNull
        @NotEmpty(message = "O pedido deve ter ao menos um item")
        List<ReqOrderItemDto> items
) {}