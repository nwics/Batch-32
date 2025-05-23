package com.codeid.eshopper.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// import com.codeid.eshopper.entities.Region;
import com.codeid.eshopper.model.Regions;

@Repository
public interface RegionsRepository extends JpaRepository<Regions, Long> {

}
