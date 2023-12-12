package com.dannyhromau.quote.api.dto;

import com.dannyhromau.quote.core.base.BaseDto;
import com.dannyhromau.quote.model.Quote;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto extends BaseDto {
    private String login;
    private String email;
    @JsonIgnore
    private List<VoteDto> voteDtoList;
    @JsonIgnore
    private List<QuoteDto> quoteDtoList;

}
