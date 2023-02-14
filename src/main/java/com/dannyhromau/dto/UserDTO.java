package com.dannyhromau.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDTO {
    private String login;
    private String password;
    private String email;
    private LocalDate localDate;
}
