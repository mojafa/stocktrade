package com.example.stocktrade.services;

import com.example.stocktrade.dto.CreateTradeRequest;
import com.example.stocktrade.models.*;
import com.example.stocktrade.models.Trade;
import com.example.stocktrade.repositories.TradeRepository;
import com.example.stocktrade.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<?> createTrade(CreateTradeRequest request) {
        Trade trade = new Trade();
        //Optional<Trade> tradeFind = tradeRepository.findById(trade.getId());
        if (!(request == null)) {
            return getResponseEntity(request, trade);

        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    //findAll
    @Transactional
    public ResponseEntity<List<Trade>> findAllTrades() {
        //List<Trade> trade = tradeRepository.findAll();
        return new ResponseEntity<>(tradeRepository.findAllByOrderByIdAsc(), HttpStatus.OK);
    }


    //findByTradeId
    @Transactional
    public ResponseEntity<Trade> findTradeById(long id) {
        if (tradeRepository.findById(id).isPresent()) {
            return new ResponseEntity<>(tradeRepository.findById(id).get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //findByUserId
    @Transactional
    public ResponseEntity<List<Trade>> findTradesByUserId(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            Optional<List<Trade>> trade = tradeRepository.findByUserOrderByIdAsc(user.get());
            if (trade.isPresent())
                return new ResponseEntity<>(trade.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    // @TODO assignment

    //findByDateCreated
    @org.springframework.transaction.annotation.Transactional
    public ResponseEntity<?> findTradesByDatecreated(String date) throws ParseException{
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dateCreated = formatter.parse(date);
        System.out.println(dateCreated);
        List<Trade> trades = tradeRepository.findTradeByDateCreated(dateCreated);
        if(!trades.isEmpty()){
            return new ResponseEntity<>(trades,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


    //findByDateUpdated
    @Transactional
    public ResponseEntity<?> findTradesByDateUpdated(String date) throws ParseException{
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dateUpdated = formatter.parse(date);
        System.out.println(dateUpdated);
        List<Trade> trades = tradeRepository.findTradeByDateUpdated(dateUpdated);
        if(!trades.isEmpty()){
            return new ResponseEntity<>(trades,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


    //findByDateCreatedBetween
    @Transactional
    public ResponseEntity<?> findTradesByDateCreatedBetween(String date1, String date2) throws ParseException{
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dateCreated1 = formatter.parse(date1);
        Date dateCreated2 = formatter.parse(date2);
        System.out.println(dateCreated1);
        System.out.println(dateCreated2);
        List<Trade> trades = tradeRepository.findTradesByDateCreatedBetween(dateCreated1,dateCreated2);
        if(!trades.isEmpty()){
            return new ResponseEntity<>(trades,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }



    //findBySymbol
    @Transactional
    public ResponseEntity<?> findTradesBySymbol(String symbol){
        List<Trade> trades = tradeRepository.findTradesBySymbol(symbol);
        if(!trades.isEmpty()){
            return new ResponseEntity<>(trades,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    //findBySymbolAndType
    @Transactional
    public ResponseEntity<?> findTradesBySymbolAndType(String symbol, String type){
        List<Trade> trades = tradeRepository.findTradesBySymbolAndType(symbol,type);
        if(!trades.isEmpty()){
            return new ResponseEntity<>(trades,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    //update
    @Transactional
    public ResponseEntity<?> updateTrade(CreateTradeRequest request, Long id) {
        Trade trade =  tradeRepository.findById(id).orElseThrow(()->new NullPointerException("Not Found"));
        if (tradeRepository.findById(id).isPresent()) {
            return getResponseEntity(request, trade);
        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<?> getResponseEntity(CreateTradeRequest request, Trade trade) {
        userRepository.save(request.user());
        trade.setType(request.type());
        trade.setUser(request.user());
        trade.setSymbol(request.symbol());
        trade.setShares(request.shares());
        trade.setPrice(request.price());
        tradeRepository.save(trade);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

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
