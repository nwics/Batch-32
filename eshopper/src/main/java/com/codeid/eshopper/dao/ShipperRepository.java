package com.codeid.eshopper.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codeid.eshopper.model.Shippers;

@Repository
public interface ShipperRepository extends JpaRepository<Shippers, Long> {

}
