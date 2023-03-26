package com.LocalisFood.LocalisFood.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private Long   productId;
    private String productName;
    private String url;
    private String description;
}
