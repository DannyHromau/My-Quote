package com.dannyhromau.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class QuoteDTO {
    private String content;
    private LocalDate localDate;
    private int ownerId;
}
