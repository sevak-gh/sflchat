package com.sfl.chat.repository;

import java.util.List;
import java.util.Optional;

/**
 * base repository for domain objects.
 *
 * @author Sevak Gharibian
 */
public interface BaseRepository<T> {
    List<T> findAll();
    Optional<T> findById(Long id);
    T save(T entity);
    Long count();
}

