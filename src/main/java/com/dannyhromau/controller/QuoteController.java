package com.dannyhromau.controller;

import com.dannyhromau.dto.QuoteDTO;
import com.dannyhromau.model.Quote;
import com.dannyhromau.repository.QuoteRepository;
import com.dannyhromau.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuoteController {
    @Autowired
    private QuoteService quoteService;


    @PostMapping("/quotes")
    public boolean addQuote(@RequestBody QuoteDTO quoteDTO){
        return quoteService.createQuote(quoteDTO);
    }

    @DeleteMapping("/quotes/{id}")
    public boolean deleteQuote(@PathVariable int id){

        return quoteService.deleteQuote(id);
    }

    @PutMapping("/quotes")
    public boolean updateQuote(@RequestBody Quote quote){
        return quoteService.updateQuote(quote);
    }

    @GetMapping("/quotes/top")
    public List<Quote> getTopQuotes(){
       return quoteService.getTopQuotes();
    }
    @GetMapping("/quotes/worse")
    public List<Quote> getWorseQuotes(){
        return quoteService.getWorseQuotes();
    }
    @GetMapping("/quotes/random")
    public Quote quote(){
        return quoteService.getRandomQuote();
    }
}
