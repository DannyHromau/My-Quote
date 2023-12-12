package com.dannyhromau.quote.repository;

import com.dannyhromau.quote.model.Quote;
import com.dannyhromau.quote.model.User;
import com.dannyhromau.quote.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VoteRepository extends JpaRepository<Vote, UUID> {
    Vote findVoteByUserAndQuote(User user, Quote quote);
    Vote findByUserIdAndQuoteId(UUID userId, UUID quoteId);
    @Query(nativeQuery = true, value = "SELECT * FROM VOTES ORDER BY date LIMIT :limit")
    List<Vote> findLastVotes(int limit);
}
