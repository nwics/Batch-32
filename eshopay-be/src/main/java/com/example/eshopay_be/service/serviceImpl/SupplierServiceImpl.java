package com.example.eshopay_be.service.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.eshopay_be.dao.SuppliersRepository;
import com.example.eshopay_be.dto.SupplierDTO;
import com.example.eshopay_be.model.Suppliers;
// import com.example.eshopay_be.service.BaseService;
import com.example.eshopay_be.service.SupplierService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class SupplierServiceImpl implements SupplierService {

    private final SuppliersRepository suppliersRepository;

    public static SupplierDTO mapToDto(Suppliers suppliers) {
        return new SupplierDTO(suppliers.getSupplierId(), suppliers.getCompanyName());
    }

    @Override
    public List<SupplierDTO> findAll() {

        log.debug("request fetching data suppliers");
        return this.suppliersRepository.findAll()
                .stream()
                .map(SupplierServiceImpl::mapToDto)
                .collect(Collectors.toList());

    }

    @Override
    public SupplierDTO findById(Long id) {

        log.debug("Request to get supplier : {}", id);

        return this.suppliersRepository.findById(id).map(SupplierServiceImpl::mapToDto)
                .orElseThrow(() -> new EntityNotFoundException("supplier not found with id " + id));
    }

    @Override
    public SupplierDTO save(SupplierDTO entity) {

        log.debug("Request to create department : {}", entity);

        Suppliers suppliers = new Suppliers();
        suppliers.setCompanyName(entity.getCompanyName());
        return mapToDto(this.suppliersRepository.save(suppliers));
    }

    @Override
    public SupplierDTO update(Long id, SupplierDTO entity) {

        log.debug("Request to update Department : {}", id);

        var supplier = this.suppliersRepository
                .findById(id)
                .orElse(null);

        supplier.setCompanyName(entity.getCompanyName());
        this.suppliersRepository.save(supplier);
        return mapToDto(supplier);
    }

    @Override
    public void delete(Long id) {

        log.debug("Request to delete Department : {}", id);

        var supplier = this.suppliersRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Cannot find Department with id " + id));

        this.suppliersRepository.delete(supplier);
    }

}
