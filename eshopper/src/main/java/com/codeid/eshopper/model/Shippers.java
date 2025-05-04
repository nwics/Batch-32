package com.codeid.eshopper.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "shippers", schema = "oe")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Shippers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipper_id")
    private Long shipperId;

    @Column(name = "company_name")
    @Size(max = 25, message = "nama company maksimal 25 karakter")
    @NotBlank(message = "nama company wajib diisi")
    private String companyName;

    @Column(name = "phone")
    @NotBlank(message = "phone wajib diisi")
    private String phone;

}
