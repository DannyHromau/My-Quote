package com.dannyhromau.quote.core.base;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface BaseService<Entity extends BaseEntity> {
    List<Entity> getAll(Pageable pageable);

    Entity getById(UUID id);

    Entity add(Entity entity);

    UUID deleteById(UUID id);

    Entity update(Entity entity);

}
