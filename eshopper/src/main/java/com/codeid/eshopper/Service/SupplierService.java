package com.codeid.eshopper.Service;

import java.util.List;
import java.util.Optional;

import com.codeid.eshopper.model.Suppliers;

public interface SupplierService {

    List<Suppliers> getAllData();

    Suppliers addDataSupplier(Suppliers suppliers);

    void deleteDataSupplier(Long id);

    Optional<Suppliers> findBySupplierId(Long id);
}
