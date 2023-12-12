package com.dannyhromau.quote.controller.impl;

import com.dannyhromau.quote.api.dto.QuoteDto;
import com.dannyhromau.quote.model.Quote;
import com.dannyhromau.quote.service.impl.QuoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuoteController {
    @Autowired
    private QuoteServiceImpl quoteServiceImpl;


    @PostMapping("/quotes")
    public boolean addQuote(@RequestBody QuoteDto quoteDTO){
        return quoteServiceImpl.createQuote(quoteDTO);
    }

    @DeleteMapping("/quotes/{id}")
    public boolean deleteQuote(@PathVariable int id){

        return quoteServiceImpl.deleteQuote(id);
    }

    @PutMapping("/quotes")
    public boolean updateQuote(@RequestBody Quote quote){
        return quoteServiceImpl.updateQuote(quote);
    }

    @GetMapping("/quotes/top")
    public List<Quote> getTopQuotes(){
       return quoteServiceImpl.getTopQuotes();
    }
    @GetMapping("/quotes/worse")
    public List<Quote> getWorseQuotes(){
        return quoteServiceImpl.getFlopQuotes();
    }
    @GetMapping("/quotes/random")
    public Quote quote(){
        return quoteServiceImpl.getRandomQuote();
    }
}
