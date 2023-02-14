package com.dannyhromau.service;


import com.dannyhromau.dto.QuoteDTO;
import com.dannyhromau.model.Quote;
import com.dannyhromau.model.User;
import com.dannyhromau.model.Vote;
import com.dannyhromau.model.VoteValue;
import com.dannyhromau.repository.QuoteRepository;
import com.dannyhromau.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuoteService {
    @Autowired
    private QuoteRepository quoteRepository;
    private UserRepository userRepository;

    public boolean createQuote(QuoteDTO quoteDTO) {
        User user = userRepository.findById(quoteDTO.getOwnerId()).get();
        Quote quote = new Quote(quoteDTO.getContent(), quoteDTO.getLocalDate(), user);
        boolean isCreated = !quote.getContent().isEmpty();
        if (isCreated) {
            quoteRepository.save(quote);
        }
        return isCreated;
    }

    public boolean updateQuote(Quote updatedQuote) {
        Quote quote;
        boolean isUpdated = false;
        Optional<Quote> quoteOpt = quoteRepository.findById(updatedQuote.getId());
        if (quoteOpt.isPresent()) {
            quote = quoteOpt.get();
            quote.setContent(updatedQuote.getContent());
            quoteRepository.save(quote);
            isUpdated = true;
        }
        return isUpdated;
    }

    public boolean deleteQuote(int id) {

        boolean isDeleted = false;
        Optional<Quote> quoteOpt = quoteRepository.findById(id);
        if (quoteOpt.isPresent()) {
            quoteRepository.deleteById(id);
            isDeleted = true;
        }
        return isDeleted;
    }

    public List<Quote> quotes() {
        List<Quote> quotes = new ArrayList<>();
        for (Quote quote : quoteRepository.findAll()) {
            quotes.add(quote);
        }
        return quotes;
    }

    public Quote getRandomQuote() {
        return quoteRepository.findRandomQuote();
    }

    public Quote getQuote(int id) {
        Optional<Quote> quoteOpt = quoteRepository.findById(id);
        Quote quote = new Quote();
        if (quoteOpt.isPresent()) {
            quote = quoteOpt.get();
        }
        return quote;
    }

    public List<Quote> getTopQuotes() {
        Map<Quote, Integer> topQuotesMap = new HashMap<>();
        for (Quote quote : quoteRepository.findAll()) {
            int plusCount = 0;
            for (Vote vote : quote.getVotes()) {
                if (vote.getVoteValue().equals(VoteValue.PLUS)) {
                    plusCount++;
                }
            }
            topQuotesMap.put(quote, plusCount);
        }
        HashMap<Quote, Integer> topTenQuotesMap = topQuotesMap.entrySet().stream().sorted(Map.Entry.<Quote, Integer>comparingByValue().reversed()).limit(10).collect(Collectors
                .toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
        return new ArrayList<>(topTenQuotesMap.keySet());
    }

    public List<Quote> getWorseQuotes() {
        Map<Quote, Integer> worseQuotesMap = new HashMap<>();
        for (Quote quote : quoteRepository.findAll()) {
            int minusCount = 0;
            for (Vote vote : quote.getVotes()) {
                if (vote.getVoteValue().equals(VoteValue.MINUS)) {
                    minusCount++;
                }
            }
            worseQuotesMap.put(quote, minusCount);
        }
        HashMap<Quote, Integer> worseTenQuotesMap = worseQuotesMap.entrySet().stream().sorted(Map.Entry.<Quote, Integer>comparingByValue().reversed()).limit(10).collect(Collectors
                .toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
        return new ArrayList<>(worseTenQuotesMap.keySet());

    }
}
