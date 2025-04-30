package com.codeid.eshopper.Service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codeid.eshopper.Service.RegionService;
import com.codeid.eshopper.dao.RegionsRepository;
// import com.codeid.eshopper.entities.Region;
import com.codeid.eshopper.model.Regions;

@Service
public class RegionServiceImpl implements RegionService {

    private final RegionsRepository regionsRepository;

    public RegionServiceImpl(RegionsRepository regionsRepository) {
        this.regionsRepository = regionsRepository;
    }

    @Override
    public List<Regions> findAllCategory() {

        return regionsRepository.findAll();
    }

}
