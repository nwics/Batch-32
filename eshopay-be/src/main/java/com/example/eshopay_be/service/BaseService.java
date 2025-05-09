package com.example.eshopay_be.service;

import java.util.List;

public interface BaseService<T, ID> {

    List<T> findAll();

    T findById(ID id);

    T save(T entity);

    T update(ID id, T entity);

    void delete(ID id);

}
