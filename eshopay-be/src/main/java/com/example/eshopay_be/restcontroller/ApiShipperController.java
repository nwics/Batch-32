package com.example.eshopay_be.restcontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.eshopay_be.dto.ApiResponse;
import com.example.eshopay_be.dto.ShippersDTO;
import com.example.eshopay_be.dto.SupplierDTO;
import com.example.eshopay_be.service.BaseService;
import com.example.eshopay_be.service.ShipperService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/shipper")
public class ApiShipperController extends ApiBaseController<ShippersDTO, Long> {

    private final ShipperService shipperService;

    @Override
    protected BaseService<ShippersDTO, Long> getService() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getService'");
        return shipperService;
    }

    @Override
    public ResponseEntity<ApiResponse<ShippersDTO>> create(@Valid @RequestBody ShippersDTO shippersDTO) {
        return super.create(shippersDTO);
    }

    @Override
    public ResponseEntity<ApiResponse<Void>> delete(Long id) {
        return super.delete(id);
    }

    @Override
    public ResponseEntity<ApiResponse<ShippersDTO>> getById(Long id) {
        return super.getById(id);
    }

    @Override
    public ResponseEntity<ApiResponse<ShippersDTO>> update(Long id, @Valid ShippersDTO entity) {
        return super.update(id, entity);
    }

}
