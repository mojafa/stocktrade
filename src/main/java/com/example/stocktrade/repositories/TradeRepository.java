package com.example.stocktrade.repositories;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TradeRepository extends JpaRepository<Trade,Long> {
    Pageable sortedBySymbolAndDateCreated =
            PageRequest.of(0, 5, Sort.by("symbol").descending().and(Sort.by("date_created")));
    Optional<List<Trade>> findByUserOrderByIdAsc(User user);

    List<Trade> findAllByOrderByIdAsc();

    @Query("select t.symbol,t.price,t.type from Trade t where t.symbol=:symbol and\n" +
            "t.dateCreated between date(:startDate) and date(:endDate)+1")
    List<Trade>
    findTradesBySymbolAndTypeAndDateCreated(@Param("symbol") String symbol,
                                            @Param("startDate") Date startDate, @Param("endDate") Date endDate);
    List<Trade> findTradesBySymbolAndTypeAndDateCreated(String symbol, Pageable pageable,Date startDate,Date endDate);

}
