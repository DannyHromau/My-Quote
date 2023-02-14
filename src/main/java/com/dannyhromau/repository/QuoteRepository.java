package com.dannyhromau.repository;

import com.dannyhromau.model.Quote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends CrudRepository<Quote, Integer> {

    Quote findQuoteByContent(String content);
    @Query(nativeQuery = true, value = "SELECT * FROM QUOTES ORDER BY rand() limit 1")
    Quote findRandomQuote();
}
