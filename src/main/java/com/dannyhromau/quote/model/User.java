package com.dannyhromau.quote.model;

import com.dannyhromau.quote.core.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "usr")
public class User extends BaseEntity {
    @Column(name = "login", nullable = false)
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Quote> quotes;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Vote> votes;

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}