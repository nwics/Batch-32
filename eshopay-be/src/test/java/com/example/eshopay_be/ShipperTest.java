package com.example.eshopay_be;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.eshopay_be.dao.ShipperRepository;
import com.example.eshopay_be.model.Shippers;
import com.example.eshopay_be.service.serviceImpl.ShipperServiceImpl;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
public class ShipperTest {

    @Mock
    private ShipperRepository shipperRepository;

    @InjectMocks
    private ShipperServiceImpl shipperServiceImpl;

    @Test
    void testFindById() {
        Shippers mockShipper = new Shippers(1L, "Speedy Express", "081818-2121");
        when(shipperRepository.findById(1L)).thenReturn(Optional.of(mockShipper));

        var result = shipperServiceImpl.findById(1L);

        assertNotNull(result);
        assertEquals("Speedy Express", result.getCompanyName());
    }

    @Test
    void testFindById_notFound_throwsException() {
        when(shipperRepository.findById(81L)).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class, () -> {
            shipperServiceImpl.findById(81L);
        });

        assertEquals("Shipper with id 81 not found", ex.getMessage());
    }

    @Test
    void testSave_success() {
        var input = new Shippers(null, "JJC", "2200-2020");
        var saved = new Shippers(2L, "JJC", "2200-2020");

        when(shipperRepository.save(input)).thenReturn(saved);

        var inputDto = shipperServiceImpl.mapToDTO(input);

        var result = shipperServiceImpl.save(inputDto);

        assertNotNull(result.getId());
        assertEquals("JJC", result.getCompanyName());

    }

    @Test
    void testDelete_success() {

        Long shipperId = 1L;
        Shippers mockShippers = new Shippers(shipperId, "NNC", "2121-2121");

        when(shipperRepository.findById(shipperId)).thenReturn(Optional.of(mockShippers));

        doNothing().when(shipperRepository).delete(mockShippers);

        shipperServiceImpl.delete(shipperId);

        verify(shipperRepository, times(1)).findById(shipperId);
        verify(shipperRepository, times(1)).delete(mockShippers);
    }
}
