package com.sportradar.livescoreboard.repository;

import java.io.Serializable;
import java.util.Optional;

public interface CrudRepository<T, ID extends Serializable> {

    T saveOrUpdate(T entity);

    Optional<T> findById(ID id);

    Optional<T> deleteById(ID id);
}

