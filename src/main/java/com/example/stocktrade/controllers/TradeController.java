package com.example.stocktrade.controllers;

import com.example.stocktrade.services.TradeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/stocktrade")
public class TradeController {

    private final TradeService tradeService;

    public TradesController(TradeService tradeService) {
        this.tradeService = tradeService;
    }
    //create
    public ResponseEntity<TradeService> helloWorldPost(@RequestBody CreateTradeRequest request){
        return demoService.helloWorldPost(request);
    }


    //findAll


    //findByTradeId

    //findByUserId

    //update

    //findTradesBySymbolAndDateCreatedBetween

    //deleteById

    //delete All
}
