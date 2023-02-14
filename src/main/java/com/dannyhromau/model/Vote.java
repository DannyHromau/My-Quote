package com.dannyhromau.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "votes")
@NoArgsConstructor
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "user_id")
    private int userId;
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
    @Column(name = "quote_id")
    private int quoteId;
    @ManyToOne
    @JoinColumn(name = "quote_id", insertable = false, updatable = false)
    private Quote quote;
    @Enumerated(EnumType.STRING)
    private VoteValue voteValue;
    @Column(name = "date")
    private LocalDate votesDate;

    public Vote(User user, Quote quote, VoteValue voteValue, LocalDate votesDate) {
        this.user = user;
        this.quote = quote;
        this.voteValue = voteValue;
        this.votesDate = votesDate;
    }


}
