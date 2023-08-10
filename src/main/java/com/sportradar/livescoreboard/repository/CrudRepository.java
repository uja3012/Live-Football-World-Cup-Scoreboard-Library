package com.sportradar.livescoreboard.repository;

import java.io.Serializable;

public interface CrudRepository<T, ID extends Serializable> {

    T save(T entity);

    T findById(ID id);

    T deleteById(ID id);
}

