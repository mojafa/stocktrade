package com.example.stocktrade.dto;

import jakarta.validation.constraints.NotEmpty;

public record User (
    Long id,
    @NotEmpty(message = "Name is required")
    String username
)

{}
