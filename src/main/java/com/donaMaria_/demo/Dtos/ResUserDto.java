package com.donaMaria_.demo.Dtos;

import java.time.Instant;

public record ResUserDto(
    Long id,
    String name,
    String email,
    String role,
    Instant create_date
) {
}
