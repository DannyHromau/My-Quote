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

    @Query(nativeQuery = true, value = "SELECT * FROM QUOTES ORDER BY rand() limit :limit")
    Quote findRandomQuote(int limit);

    List<Quote> findByNegativeCountDesc(Pageable pageable);
    List<Quote> findByPositiveCountDesc(Pageable pageable);
}
