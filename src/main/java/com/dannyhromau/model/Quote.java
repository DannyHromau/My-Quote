package com.dannyhromau.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "quotes")
@NoArgsConstructor
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String content;
    @Column(name = "date")
    private LocalDate actualContentDate;
    @Column(name = "user_id")
    private int userId;
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
    @OneToMany
    private List<Vote> votes;

    public Quote(String content, LocalDate actualContentDate, User user) {
        this.content = content;
        this.actualContentDate = actualContentDate;
        this.user = user;

    }

}
