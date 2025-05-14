package com.example.eshopay_be.dto;

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
public class ProductPhotoDTO {
    private Long producttoId;
    private String fileName;
    private float fileSize;
    private String fileType;
    private String fileUri;
    private Long productId;
}
