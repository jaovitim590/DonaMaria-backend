package com.donaMaria_.demo.Dtos;

import java.time.Instant;

public record ApiErrorDto(
        int status,
        String message,
        Instant timestamp
) {}