package com.dannyhromau.controller;

import com.dannyhromau.dto.VoteDTO;
import com.dannyhromau.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VoteController {
    @Autowired
    private VoteService voteService;

    @PostMapping("/vote/")
    public ResponseEntity makeVote(@RequestBody VoteDTO voteDTO){
        voteService.makeVote(voteDTO.getUserId(), voteDTO.getQuoteId(), voteDTO.getValue(), voteDTO.getLocalDate());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
