package com.sfl.chat.service;

import java.util.List;

/**
 * base service interface for domain objects.
 *
 * @author Sevak Gharibian
 */
public interface BaseService<T> {
    List<T> findAll();
    T findById(Long id);
    T save(T entity);
    Long getCount();
}
