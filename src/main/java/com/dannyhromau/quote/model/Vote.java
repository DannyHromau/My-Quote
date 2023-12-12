package com.dannyhromau.quote.model;

import com.dannyhromau.quote.core.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "vote")
public class Vote extends BaseEntity {
    @Column(name = "user_id")
    private UUID userId;
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
    @Column(name = "quote_id")
    private UUID quoteId;
    @ManyToOne
    @JoinColumn(name = "quote_id", insertable = false, updatable = false)
    private Quote quote;
    @Enumerated(EnumType.STRING)
    private VoteValue voteValue;

}
