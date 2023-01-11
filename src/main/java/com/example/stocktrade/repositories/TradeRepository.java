package com.example.stocktrade.repositories;

import com.example.stocktrade.models.Trade;
import com.example.stocktrade.models.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {

    Pageable sortedBySymbolAndDateCreated =
            PageRequest.of(0, 5, Sort.by("symbol").descending().and(Sort.by("date_created")));
    Optional<List<Trade>> findByUserOrderByIdAsc(User user);

    List<Trade> findAllByOrderByIdAsc();

    List<Trade> findTradeByDateCreated(Date date);
    List<Trade> findTradeByDateUpdated(Date date);

    List<Trade> findTradesByDateCreatedBetween(Date date1, Date date2);

    List<Trade> findTradesBySymbol(String symbol);
    List<Trade> findTradesBySymbolAndType(String symbol, String type);






    //List<Trade> findTradesBySymbolAndDateCreatedBetween(String symbol,Date startDate, Date endDate);
}