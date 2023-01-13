package com.example.stocktrade.controllers;

import com.example.stocktrade.dto.CreateTradeRequest;
import com.example.stocktrade.models.Trade;
import com.example.stocktrade.repositories.TradeRepository;
import com.example.stocktrade.services.TradeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/stocktrade")
public class TradeController {

    private final TradeService tradeService;
    private final TradeRepository tradeRepository;

    public TradeController(TradeService tradeService,
                           TradeRepository tradeRepository) {
        this.tradeService = tradeService;
        this.tradeRepository = tradeRepository;
    }
    //createTrades
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateTradeRequest request){
        return tradeService.createTrade(request);
    }


    //findAllTrades
    @GetMapping
    public ResponseEntity<List<Trade>> findAllTrades(){
        return tradeService.findAllTrades();
    }


    //findByTradeId
    @GetMapping("/{id}")
    public ResponseEntity<?> findTradeById(@PathVariable(value="id") Long id){
        return tradeService.findTradeById(id);
    }


    //findByUserId
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Trade>> findTradeByUserId(@PathVariable(value="userId") Long userId){
        return tradeService.findTradesByUserId(userId);
    }

    //updateTrades
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTrade(@RequestBody CreateTradeRequest request, @PathVariable(value="id") Long id){
        return tradeService.updateTrade( request, id);
    }
    //findTradeByDateCreated
    @GetMapping(value="/dateCreated/{date}")
    public ResponseEntity<?> findTradesByDate(@PathVariable(value = "date") String date) throws ParseException {
        return tradeService.findTradesByDatecreated(date);
    }

    //findByDateUpdated
    @GetMapping(value="/dateUpdated/{date}")
    public ResponseEntity<?> findTradesByDateUpdated(@PathVariable(value = "date") String date) throws ParseException {
        return tradeService.findTradesByDateUpdated(date);
    }

    //findByDateCreatedBetween
    @GetMapping(value="/dateCreatedBetween/{date1}/{date2}")
    public ResponseEntity<?> findTradesByDateCreatedBetween(@PathVariable(value = "date1") String dateCreated1, @PathVariable(value = "date2") String dateCreated2) throws ParseException {
        return tradeService.findTradesByDateCreatedBetween(dateCreated1, dateCreated2);
    }


    //findBySymbol
@GetMapping(value="/symbol/{symbol}")
public ResponseEntity<?> findTradesBySymbol(@PathVariable(value = "symbol") String symbol){
        return tradeService.findTradesBySymbol(symbol);
    }




    //findBySymbolAndType
    @GetMapping(value="/symbol/{symbol}/type/{type}")
    public ResponseEntity<?> findTradesBySymbolAndType(@PathVariable(value = "symbol") String symbol, @PathVariable(value = "type") String type){
        return tradeService.findTradesBySymbolAndType(symbol, type);
    }




    //deleteById
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(value="id") Long id){
        return tradeService.deleteById(id);
    }

    //delete All
    @DeleteMapping
    public  ResponseEntity<String> deleteAll(){
            tradeService.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
    }
}
