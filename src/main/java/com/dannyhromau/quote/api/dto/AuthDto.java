package com.dannyhromau.quote.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthDto {
    private String login;
    private String password;
    private String email;
}
