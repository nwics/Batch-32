package com.codeid.eshopper.Service;

import java.util.List;
import java.util.Optional;

import com.codeid.eshopper.model.Shippers;

public interface ShipperService {

    List<Shippers> getAllShipper();

    Shippers addShippers(Shippers shippers);

    Optional<Shippers> findShipperById(Long shipperId);

    void deleteShipperById(Long shipperId);

}
