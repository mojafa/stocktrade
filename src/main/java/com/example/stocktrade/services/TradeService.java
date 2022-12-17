package com.example.stocktrade.services;

import com.example.stocktrade.model.*;
import com.example.stocktrade.models.Trade;
import com.example.stocktrade.repositories.TradeRepository;
import com.example.stocktrade.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.util.List;

@Service
public class TradeService {

    private final TradeRepository tradeRepository;
    private final UserRepository userRepository;

    public TradeService(TradeRepository tradeRepository, UserRepository userRepository) {
        this.tradeRepository = tradeRepository;
        this.userRepository = userRepository;
    }


    //create
@Transactional
public ResponseEntity<Trade> createTrade(Trade request){
    Trade trade = new Trade();
   trade.setType(request.getType());
    trade.setUser(request.getUser());
    trade.setSymbol(request.getSymbol());
    trade.setShares(request.getShares());
    trade.setPrice(request.getPrice());
   // trade.setTimestamp(Timestamp.valueOf((String)request.getTimestamp()));
    tradeRepository.save(trade);
    return ResponseEntity.status(201).body(trade);
}


    //findAll
    @Transactional
    public ResponseEntity<List<Trade>> findAllTrades(Trade request){
        List<Trade> trade = tradeRepository.findAll();
        return ResponseEntity.status(200).body(trade);
    }



    //findByTradeId
    @Transactional
    public ResponseEntity<Trade> findTradeById(long id){
        if (tradeRepository.findById(id).isPresent()) {
            return new ResponseEntity<>(tradeRepository.findById(id).get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


   // @TODO ASSIGNMENT
    //@TODO ADD AUTHENTICATION
    //findByUserId
//    @Transactional
//    public ResponseEntity<List<Trade>> findTradesByUserId(long id){
//        if (tradeRepository.findTradesByUserId(id).isPresent()) {
//            return new ResponseEntity<>(tradeRepository.findTradesByUserId(id).get(), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }



    // @TODO ASSIGNMENT

    //findTradesBySymbolAndDateCreatedBetween
//    @Transactional
//    public ResponseEntity<List<Trade>> findTradesBySymbolAndDateCreatedBetween(String symbol, String startDate,String endDate){
//        if (tradeRepository.findTradesBySymbolAndDateCreatedBetween(symbol,startDate,endDate).isPresent()) {
//            return new ResponseEntity<>(tradeRepository.findTradesBySymbolAndDateCreatedBetween(symbol,startDate,endDate).get(), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//




    //delete All
    @Transactional
    public void deleteAll(){
        Trade trade = new Trade();
        tradeRepository.deleteAll();
    }

    //deleteById
    @Transactional
    public ResponseEntity<?> deleteById( long id){
        Trade trade  = new Trade();
        if (tradeRepository.findById(id) !=null){
            tradeRepository.deleteById(id);

        }
        return new ResponseEntity<>(null, null, 200);
    }
}
