package com.example.eshopay_be.service;

import java.util.List;

import com.example.eshopay_be.dto.ApiResponsePagination;

public interface BaseService<T, ID> {

    // List<T> findAll();
    ApiResponsePagination<T> findAll(Integer size, Integer current);

    T findById(ID id);

    T save(T entity);

    T update(ID id, T entity);

    void delete(ID id);

}
