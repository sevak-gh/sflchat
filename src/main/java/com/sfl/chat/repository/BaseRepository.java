package com.sfl.chat.repository;

import java.util.List;

/**
 * base repository for domain objects.
 *
 * @author Sevak Gharibian
 */
public interface BaseRepository<T> {
    List<T> findAll();
    T findById(Long id);
    T save(T entity);
    Long count();
}

