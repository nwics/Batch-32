package com.codeid.eshopper.Service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codeid.eshopper.Service.ShipperService;
import com.codeid.eshopper.dao.ShipperRepository;
import com.codeid.eshopper.model.Shippers;

@Service
public class ShipperServiceImpl implements ShipperService {

    private final ShipperRepository shipperRepository;

    public ShipperServiceImpl(ShipperRepository shipperRepository) {
        this.shipperRepository = shipperRepository;
    }

    @Override
    public List<Shippers> getAllShipper() {
        return shipperRepository.findAll();
    }

    @Override
    public Shippers addShippers(Shippers shippers) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'addShippers'");
        return shipperRepository.save(shippers);
    }

    @Override
    public Optional<Shippers> findShipperById(Long shipperId) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'findShipperById'");
        return shipperRepository.findById(shipperId);
    }

    @Override
    public void deleteShipperById(Long shipperId) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'deleteShipperById'");
        Shippers shipper = shipperRepository.findById(shipperId).orElse(null);
        if (shipper != null) {
            shipperRepository.delete(shipper);
        }

    }

}
