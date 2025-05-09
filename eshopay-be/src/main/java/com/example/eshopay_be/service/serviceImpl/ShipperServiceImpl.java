package com.example.eshopay_be.service.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.eshopay_be.dao.ShipperRepository;
import com.example.eshopay_be.dto.ShippersDTO;
import com.example.eshopay_be.model.Shippers;
import com.example.eshopay_be.model.Suppliers;
import com.example.eshopay_be.service.ShipperService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShipperServiceImpl implements ShipperService {

    private final ShipperRepository shipperRepository;

    public static ShippersDTO mapToDTO(Shippers shippers) {
        return new ShippersDTO(shippers.getShipperId(), shippers.getCompanyName(), shippers.getPhone());
    }

    @Override
    public List<ShippersDTO> findAll() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'findAll'");
        log.debug("request to get data shippers");
        return this.shipperRepository.findAll().stream().map(ShipperServiceImpl::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public ShippersDTO findById(Long id) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'findById'");
        log.debug("request to get data id shippers");
        return this.shipperRepository.findById(id).map(ShipperServiceImpl::mapToDTO)
                .orElseThrow(() -> new EntityNotFoundException("shipper id not found " + id));
    }

    @Override
    public ShippersDTO save(ShippersDTO entity) {
        // TODO Auto-generated method stub
        log.debug("request to create shipper");
        // throw new UnsupportedOperationException("Unimplemented method 'save'");
        Shippers shippers = new Shippers();
        shippers.setCompanyName(entity.getCompanyName());
        shippers.setPhone(entity.getPhone());
        return mapToDTO(shipperRepository.save(shippers));
    }

    @Override
    public ShippersDTO update(Long id, ShippersDTO entity) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'update'");
        log.debug("request to update data");
        Shippers shippers = shipperRepository.findById(id).orElse(null);
        shippers.setCompanyName(entity.getCompanyName());
        shippers.setPhone(entity.getPhone());
        shipperRepository.save(shippers);
        return mapToDTO(shippers);
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'delete'");
        log.debug("request to get data id" + id);
        Shippers shippers = shipperRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("cannot find shipper id" + id));
        shipperRepository.delete(shippers);
    }

}
