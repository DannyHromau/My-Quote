package com.dannyhromau.quote.repository;

import com.dannyhromau.quote.model.Quote;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, UUID> {

    @Query(nativeQuery = true, value = "SELECT * FROM quote ORDER BY rand() limit :limit")
    Quote findRandomQuote(int limit);
    @Query(nativeQuery = true, value = "SELECT * FROM quote WHERE id IN (" +
            "SELECT quote_id FROM vote " +
            "WHERE vote_value = 'PLUS' " +
            "GROUP BY (quote_id) " +
            "ORDER BY COUNT(*) DESC LIMIT :limit)")
    List<Quote> findTopQuotes(int limit);
    @Query(nativeQuery = true, value = "SELECT * FROM quote WHERE id IN (" +
            "SELECT quote_id FROM vote " +
            "WHERE vote_value = 'MINUS'" +
            "GROUP BY (quote_id)" +
            "ORDER BY COUNT(*) DESC LIMIT :limit)")
    List<Quote> findFlopQuotes(int limit);

}
