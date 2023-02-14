package com.dannyhromau.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VoteDTO {
    private int userId;
    private int quoteId;
    private LocalDate localDate;
    private String value;
}
