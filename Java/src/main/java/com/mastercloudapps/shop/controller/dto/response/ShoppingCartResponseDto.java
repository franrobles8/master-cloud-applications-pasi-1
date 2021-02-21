package com.mastercloudapps.shop.controller.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.mastercloudapps.shop.domain.dto.FullShoppingCartDto;

import org.modelmapper.ModelMapper;

public class ShoppingCartResponseDto {
    
    private Long id;

    private List<ProductResponseDto> products;

    private boolean complete;

    public ShoppingCartResponseDto() {
    }

    public ShoppingCartResponseDto(Long id, List<ProductResponseDto> products, boolean complete) {
        this.id = id;
        this.products = products;
        this.complete = complete;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProductResponseDto> getProducts() {
        return this.products;
    }

    public void setProducts(List<ProductResponseDto> products) {
        this.products = products;
    }

    public boolean isComplete() {
        return this.complete;
    }

    public boolean getComplete() {
        return this.complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public static ShoppingCartResponseDto fromFullShoppingCartDto(FullShoppingCartDto fullShoppingCart) {
        List<ProductResponseDto> products = fullShoppingCart.getProducts()
            .stream()
            .map(product -> new ModelMapper().map(product, ProductResponseDto.class))
            .collect(Collectors.toList());

        return new ShoppingCartResponseDto(
            fullShoppingCart.getId(), 
            products, 
            fullShoppingCart.isCompleted());
    }
}
