package com.example.stocktrade.controllers;

import com.example.stocktrade.dto.CreateTradeRequest;
import com.example.stocktrade.dto.GenericResponse;
import com.example.stocktrade.models.Trade;
import com.example.stocktrade.repositories.TradeRepository;
import com.example.stocktrade.services.TradeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
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
    //create
    @PostMapping
    public ResponseEntity<String> create(@RequestBody CreateTradeRequest request){
        return tradeService.createTrade(request);
    }


    //findAll
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

    //update
    @PutMapping("/{id}")
    public ResponseEntity<String> updateTrade(@RequestBody CreateTradeRequest request){
        return tradeService.updateTrade(request);
    }
    //findTradeByDateCreated
    @GetMapping("/{id}/{date}")
    public ResponseEntity<List<Trade>> findTradeByDateCreated(@PathVariable(value="id") Long id, @PathVariable(value="date") String date) throws ParseException {
        return tradeService.findTradeByDateCreated(date, id);
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
