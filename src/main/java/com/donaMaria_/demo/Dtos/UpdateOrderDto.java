package com.donaMaria_.demo.Dtos;

import com.donaMaria_.demo.models.OrderStatus;

public record UpdateOrderDto(
        OrderStatus status
) {}