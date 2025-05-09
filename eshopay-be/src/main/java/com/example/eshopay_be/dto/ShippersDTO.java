package com.example.eshopay_be.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShippersDTO {

    private Long id;
    @Size(max = 30, message = "tidak boleh lebih dari 30 character")
    private String companyName;
    private String phone;
}
