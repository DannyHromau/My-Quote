package com.dannyhromau.quote;

import com.dannyhromau.quote.model.Quote;
import com.dannyhromau.quote.model.User;
import com.dannyhromau.quote.model.Vote;
import com.dannyhromau.quote.model.VoteValue;
import com.dannyhromau.quote.repository.QuoteRepository;
import com.dannyhromau.quote.repository.UserRepository;
import com.dannyhromau.quote.repository.VoteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@DataJpaTest
@DisplayName("Testing of quote jpa")
@TestPropertySource("classpath:application-test.yml")
public class CustomDataJpaTest {

    @Autowired
    private QuoteRepository quoteRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VoteRepository voteRepository;
    private static final int DATA_MAX_SIZE = 10;
    private int limit;
    private List<User> users = new ArrayList<>();
    private List<Quote> quotes = new ArrayList<>();
    private List<Vote> votes = new ArrayList<>();

    @BeforeEach
    void setup(){
        for (int i = 0; i < DATA_MAX_SIZE; i++){
            User user = new User("ivan", "Abcdefgh123@", "ivan@mail.ru");
            users.add(user);
        }
        userRepository.saveAll(users);
        users = userRepository.findAll();
        for (int i = 0; i < DATA_MAX_SIZE; i++){
            Quote quote = new Quote();
            quote.setContent("content");
            quote.setUser(users.get(i));
            quotes.add(quote);
        }
        quoteRepository.saveAll(quotes);
        users = userRepository.findAll();
        quotes = quoteRepository.findAll();
    }

    @Test
    @DisplayName("get top quotes when exists")
    void getTopQuotesWhenExists(){
        for (int i = 0; i < DATA_MAX_SIZE; i++){
            Vote vote = new Vote();
            vote.setQuote(quotes.get(i));
            vote.setQuoteId(quotes.get(i).getId());
            vote.setUser(users.get(i));
            vote.setUserId(users.get(i).getId());
            vote.setVoteValue(VoteValue.PLUS);
            voteRepository.save(vote);
        }
        limit = 10;
        List<Quote> topQuotes = quoteRepository.findTopQuotes(limit);
        int expectedSize = limit;
        int actualSize = topQuotes.size();
        Assertions.assertEquals(expectedSize, actualSize);
    }
    @Test
    @DisplayName("get flop quotes when exists")
    void getFlopQuotesWhenExists(){
        for (int i = 0; i < DATA_MAX_SIZE; i++){
            Vote vote = new Vote();
            vote.setQuote(quotes.get(i));
            vote.setQuoteId(quotes.get(i).getId());
            vote.setUser(users.get(i));
            vote.setUserId(users.get(i).getId());
            vote.setVoteValue(VoteValue.MINUS);
            voteRepository.save(vote);
        }
        List<Quote> topQuotes = quoteRepository.findFlopQuotes(limit);
        int expectedSize = limit;
        int actualSize = topQuotes.size();
        Assertions.assertEquals(expectedSize, actualSize);
    }

//    @Test
//    @DisplayName("get flop votes count when exists")
//    void getFlopVotesCountWhenExists(){
//        for (int i = 0; i < DATA_MAX_SIZE; i++){
//            Vote vote = new Vote();
//            vote.setQuote(quotes.get(i));
//            vote.setQuoteId(quotes.get(i).getId());
//            vote.setUser(users.get(i));
//            vote.setUserId(users.get(i).getId());
//            vote.setVoteValue(VoteValue.MINUS);
//            voteRepository.save(vote);
//        }
//
//        System.out.println(voteRepository.findVotesByQuoteId(quotes.get(0).getId()));
//        int expected = 1;
//        int actual = voteRepository.findVotesCountByQuoteIdAndVoteValue(quotes.get(0).getId(), VoteValue.MINUS);
//        Assertions.assertEquals(expected, actual);
//    }


}
