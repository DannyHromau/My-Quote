package com.dannyhromau.quote.model;

import com.dannyhromau.quote.core.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "quote")
public class Quote extends BaseEntity {
    private String content;
    @Column(name = "updated_on")
    private ZonedDateTime updatedOn;
    @Column(name = "user_id")
    private UUID userId;
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
    @OneToMany
    private List<Vote> votes;

}
