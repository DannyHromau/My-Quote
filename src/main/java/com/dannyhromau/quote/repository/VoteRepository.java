package com.dannyhromau.quote.repository;

import com.dannyhromau.quote.model.Quote;
import com.dannyhromau.quote.model.User;
import com.dannyhromau.quote.model.Vote;
import com.dannyhromau.quote.model.VoteValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VoteRepository extends JpaRepository<Vote, UUID> {
    Vote findVoteByUserAndQuote(User user, Quote quote);

    Vote findByUserIdAndQuoteId(UUID userId, UUID quoteId);

    @Query(nativeQuery = true, value = "SELECT * FROM vote ORDER BY date LIMIT :limit")
    List<Vote> findLastVotes(int limit);

//    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM vote where quote_id = :idFromUuid and vote_value = :voteValue")
    int findVotesCountByQuoteIdAndVoteValue(UUID idFromUuid, VoteValue voteValue);
    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM vote where quote_id = :idFromUuid and vote_value = :voteValue")
    int findCountByQuoteAndValue(String idFromUuid, VoteValue voteValue);
    @Query(nativeQuery = true, value = "SELECT * FROM vote where vote.quote_id = '7334169c-9b42-11ee-b9d1-0242ac120002'")
    List<Vote> findVotesByQuoteId(UUID quoteId);
    @Query(nativeQuery = true, value = "SELECT * FROM vote where vote_value = 'MINUS'")
    List<Vote> findVotesByVoteValue(VoteValue voteValue);
//    List<Vote> findVotesByVoteValue(VoteValue voteValue);

}
