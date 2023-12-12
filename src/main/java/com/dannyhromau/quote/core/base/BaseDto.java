package com.dannyhromau.quote.core.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
public abstract class BaseDto implements Serializable {
    private UUID id;
    private ZonedDateTime createdOn;
}
