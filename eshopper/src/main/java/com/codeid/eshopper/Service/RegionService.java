package com.codeid.eshopper.Service;

import java.util.List;

import org.springframework.stereotype.Service;

// import com.codeid.eshopper.entities.Region;
import com.codeid.eshopper.model.Regions;

public interface RegionService {
    List<Regions> findAllCategory();
}
