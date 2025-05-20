package com.example.eshopay_be.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.eshopay_be.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

}
