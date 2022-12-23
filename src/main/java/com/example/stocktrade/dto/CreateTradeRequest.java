package com.example.stocktrade.dto;

import com.example.stocktrade.models.User;

public record CreateTradeRequest(
        long id,
        String type,
        User user,
        String symbol,
        Integer shares,
        Float price,
        String timestamp
)
{}
