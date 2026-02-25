package com.donaMaria_.demo.Dtos;

import com.donaMaria_.demo.models.Categories;
import java.math.BigDecimal;

public record ResOrderItemDto(
        String id,
        Long productId,
        String productName,
        BigDecimal productPrice,
        Categories productCategory,
        Integer quantity
) {}