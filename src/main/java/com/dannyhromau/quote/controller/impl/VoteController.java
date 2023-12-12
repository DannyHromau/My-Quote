package com.dannyhromau.quote.controller.impl;

import com.dannyhromau.quote.api.dto.VoteDto;
import com.dannyhromau.quote.service.impl.VoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VoteController {
    @Autowired
    private VoteServiceImpl voteServiceImpl;

    @PostMapping("/vote/")
    public ResponseEntity makeVote(@RequestBody VoteDto voteDTO){
        voteServiceImpl.makeVote(voteDTO.getUserId(), voteDTO.getQuoteId(), voteDTO.getValue(), voteDTO.getLocalDate());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
