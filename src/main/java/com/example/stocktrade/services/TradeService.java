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
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    public ResponseEntity<String> createTrade(CreateTradeRequest request) {
        Trade trade = new Trade();
        //Optional<Trade> tradeFind = tradeRepository.findById(trade.getId());
        if (!(request == null)) {
            userRepository.save(request.user());
            trade.setType(request.type());
            trade.setUser(request.user());
            trade.setSymbol(request.symbol());
            trade.setShares(request.shares());
            trade.setPrice(request.price());
            //trade.setTimestamp(Timestamp.valueOf((String)request.getTimestamp()));
            tradeRepository.save(trade);
            return new ResponseEntity<>(HttpStatus.CREATED);

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
    @Transactional
public  ResponseEntity<List<Trade>> findTradeByDateCreated(String localD, long id) throws ParseException {
        if (tradeRepository.findById(id).isPresent()) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate_ = formatter.parse(startDate);
            Date endDate_ = formatter.parse(endDate);
            System.out.println(localDate);
//            Date dateCreated = (Date) formatter.parse(date);
//            System.out.println(dateCreated);
            tradeRepository.findTradeByDateCreated(localDate);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //findByDateUpdated

    //findByDateCreatedBetween


    //findBySymbol

    //findBySymbolAndType

    //findBySymbolAndTypeAndDateCreated

    //findBySymbolAndTypeAndDateCreatedBetween

    //findBySymbolAndTypeAndDateCreatedBetweenAndDateUpdatedBetween


    //findBySymbolAndTypeAndDateCreatedBetweenAndDateUpdatedBetweenAndUser



    //update
    @Transactional
    public ResponseEntity<String> updateTrade(CreateTradeRequest request) {
        Trade trade = new Trade();
        if (!(request == null)) {
            userRepository.save(request.user());
            trade.setType(request.type());
            trade.setUser(request.user());
            trade.setSymbol(request.symbol());
            trade.setShares(request.shares());
            trade.setPrice(request.price());
            tradeRepository.save(trade);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
