package com.example.eshopay_be.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutReqDTO {

    private Long userId;

    private Long shipperId;
    private String shipName; // tidak perlu
    private String shipAddress; // tidak perlu
    private String shipCity; // tidak perlu
    private String shipRegion; // tidak perlu
    private String shipPostalCode; // tidak perlu
    private String shipCountry; // tidak perlu

    // Payment information
    private String paymentType;
    // private String cardNo;
    private String bankCode;

    private Long locationId;

    private Long employeeId;
}
