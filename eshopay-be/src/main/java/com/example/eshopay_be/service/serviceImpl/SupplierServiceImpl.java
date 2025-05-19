package com.example.eshopay_be.service.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.eshopay_be.dao.SuppliersRepository;
import com.example.eshopay_be.dto.ApiResponsePagination;
import com.example.eshopay_be.dto.Pagination;
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
        if (suppliers == null) {
            return null;
        }
        return new SupplierDTO(suppliers.getSupplierId(), suppliers.getCompanyName());
    }

    public static Suppliers mapToModel(SupplierDTO supplierDTO) {
        if (supplierDTO == null) {
            return null;
        }
        return new Suppliers(supplierDTO.getSupplierId(), supplierDTO.getCompanyName());
    }

    @Override
    public ApiResponsePagination<SupplierDTO> findAll(Integer size, Integer current, String keyword,
            String categoryName, String sortingDirection) {

        Pageable pageable = PageRequest.of(current - 1, size, Sort.by("supplierId").ascending());
        Page<Suppliers> pageResult = suppliersRepository.findAll(pageable);
        List<SupplierDTO> supplierDTOs = pageResult.getContent().stream().map(SupplierServiceImpl::mapToDto)
                .collect(Collectors.toList());

        Pagination pagination = new Pagination();
        pagination.setCurrent(current);
        pagination.setSize(size);
        pagination.setTotal(pageResult.getTotalElements());
        pagination.setTotalPages(pageResult.getTotalPages());

        ApiResponsePagination<SupplierDTO> response = new ApiResponsePagination<>();
        response.setMessage("success get data");
        response.setStatusCode(200);
        response.setTimestamp(LocalDateTime.now());
        response.setPage(pagination);
        response.setData(supplierDTOs);

        return response;

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
