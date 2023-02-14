package com.dannyhromau.repository;

import com.dannyhromau.model.Quote;
import com.dannyhromau.model.User;
import com.dannyhromau.model.Vote;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends CrudRepository<Vote, Integer> {
    Vote findVoteByUserAndQuote(User user, Quote quote);
    @Query(nativeQuery = true, value = "SELECT * FROM VOTES ORDER BY date LIMIT 5")
    List<Vote> findLastVotes();
}
