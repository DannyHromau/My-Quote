package com.dannyhromau.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String login;
    private String password;
    private String email;
    @Column(name = "registered")
    private LocalDate registrationDate;
    @OneToMany
    private List<Quote> quotes;


    public User(String login, String password, String email, LocalDate registrationDate) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.registrationDate = registrationDate;
    }

}