package com.dannyhromau.service;

import com.dannyhromau.model.Quote;
import com.dannyhromau.model.User;
import com.dannyhromau.model.Vote;
import com.dannyhromau.model.VoteValue;
import com.dannyhromau.repository.QuoteRepository;
import com.dannyhromau.repository.UserRepository;
import com.dannyhromau.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VoteService {
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuoteRepository quoteRepository;

    public void makeVote(int userId, int quoteId, String voteValue, LocalDate votesDate) {
        VoteValue value = voteValue.equals("minus") ? VoteValue.MINUS : VoteValue.PLUS;
        User user = userRepository.findById(userId).get();
        Quote quote = quoteRepository.findById(quoteId).get();
        Vote exitingVote = voteRepository.findVoteByUserAndQuote(user, quote);
        if (exitingVote == null) {
            Vote newVote = new Vote(user, quote, value, votesDate);
            voteRepository.save(newVote);
        } else {
            exitingVote.setVoteValue(value);
            exitingVote.setVotesDate(votesDate);
            voteRepository.save(exitingVote);
        }
    }

    public List<Vote> getLastVotes(){
        return voteRepository.findLastVotes();
    }
}
