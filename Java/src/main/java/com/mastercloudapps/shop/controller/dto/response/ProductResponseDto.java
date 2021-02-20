package com.mastercloudapps.shop.controller.dto.response;

import com.mastercloudapps.shop.domain.dto.FullProductDto;

public class ProductResponseDto {
    
    private Long id;

    private String name;


    public ProductResponseDto() {
    }

    public ProductResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ProductResponseDto fromFullProductDto(FullProductDto product) {
        return new ProductResponseDto(product.getId(), product.getName());
    }

}
