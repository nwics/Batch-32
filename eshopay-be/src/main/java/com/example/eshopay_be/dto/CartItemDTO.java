package com.example.eshopay_be.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CartItemDTO {

    private Long cartId;
    private Long productId;
    private Integer quantity;
    private BigDecimal discount;
    private BigDecimal subTotal;
    private ProductDTO productDTO;
}
