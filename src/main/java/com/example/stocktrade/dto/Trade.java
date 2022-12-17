package com.example.stocktrade.dto;

import com.example.stocktrade.models.User;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;

public record Trade (
        Long id,
        String type,
        User user,
        String symbol,
        Integer shares,
        Float price,
        String timestamp
)
{}
