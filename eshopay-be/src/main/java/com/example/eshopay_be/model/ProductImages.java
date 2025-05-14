package com.example.eshopay_be.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "product_images", schema = "oe")
public class ProductImages extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_to_id")
    private Long productToId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_size")
    private float fileSize;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "file_uri")
    private String fileUrl;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products products;
}
