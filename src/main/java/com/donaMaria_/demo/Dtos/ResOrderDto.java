package com.donaMaria_.demo.Dtos;

import com.donaMaria_.demo.models.OrderStatus;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public record ResOrderDto(
        Long id,
        String orderNumber,
        Long userId,
        String userName,
        Date date,
        OrderStatus status,
        BigDecimal total,
        List<ResOrderItemDto> items
) {}