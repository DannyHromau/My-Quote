package com.dannyhromau.quote.api.dto;

import com.dannyhromau.quote.core.base.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuoteDto extends BaseDto {
    private String content;
    private int ownerId;
}
