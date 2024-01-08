package com.dannyhromau.quote.api.dto;

import com.dannyhromau.quote.core.base.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class VoteDto extends BaseDto {
    private UUID userId;
    private UUID quoteId;
    private String value;
}
