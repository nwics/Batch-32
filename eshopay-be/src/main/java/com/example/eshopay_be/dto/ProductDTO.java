package com.example.eshopay_be.dto;

import java.math.BigDecimal;

import com.example.eshopay_be.model.Category;
import com.example.eshopay_be.model.Suppliers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long productId;
    private String productName;
    private String quantityPerUnit;
    private BigDecimal unitPrice;
    private Integer unitsInStock;
    private Integer unitsOnOrder;
    private Integer reorderLevel;
    private Integer discontinued;
    private String pictures;
    private SupplierDTO supplierDTO;
    private CategoryDTO categoryDTO;
}
