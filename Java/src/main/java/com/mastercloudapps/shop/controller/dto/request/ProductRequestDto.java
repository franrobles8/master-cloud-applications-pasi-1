package com.mastercloudapps.shop.controller.dto.request;

public class ProductRequestDto {
    
    private String name;

    public ProductRequestDto() {
    }

    public ProductRequestDto(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
