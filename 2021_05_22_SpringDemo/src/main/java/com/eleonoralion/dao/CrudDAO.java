package com.eleonoralion.dao;

import java.util.List;
import java.util.Optional;

public interface CrudDAO<T> {
    Optional<T> find(Long id);
    void save(T model);
    void update(T model);
    void delete(T model);

    List<T> findAll();
}
