package com.codeid.eshopper.Service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codeid.eshopper.Service.SupplierService;
import com.codeid.eshopper.dao.SuppliersRepository;
import com.codeid.eshopper.model.Suppliers;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SuppliersRepository suppliersRepository;

    public SupplierServiceImpl(SuppliersRepository suppliersRepository) {
        this.suppliersRepository = suppliersRepository;
    }

    @Override
    public List<Suppliers> getAllData() {
        return suppliersRepository.findAll();
    }

    @Override
    public Suppliers addDataSupplier(Suppliers suppliers) {
        return suppliersRepository.save(suppliers);
    }

    @Override
    public void deleteDataSupplier(Long id) {
        Suppliers suppliers = suppliersRepository.findById(id).orElse(null);
        if (suppliers != null) {
            suppliersRepository.findById(id);
        }
        // return suppliersRepository.delete(id);
    }

    @Override
    public Optional<Suppliers> findBySupplierId(Long id) {
        return suppliersRepository.findById(id);
    }

}
