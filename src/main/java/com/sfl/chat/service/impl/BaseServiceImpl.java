package com.sfl.chat.service.impl;

import com.sfl.chat.service.BaseService;
import com.sfl.chat.repository.BaseRepository;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 *
 * base service implementation for domain objects.
 *
 * @author Sevak Gharibian
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    protected final BaseRepository<T> repository;

    public BaseServiceImpl(BaseRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return repository.findAll();
    }
    
    @Override
    @Transactional
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getCount() {
        return repository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public T findById(Long id) {
        return repository.findById(id);
    }
}
