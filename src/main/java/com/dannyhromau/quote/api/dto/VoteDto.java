package com.dannyhromau.quote.api.dto;

import com.dannyhromau.quote.core.base.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoteDto extends BaseDto {
    private int userId;
    private int quoteId;
    private String value;
}
