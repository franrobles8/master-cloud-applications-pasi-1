package com.mastercloudapps.shop.controller.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.mastercloudapps.shop.domain.dto.FullShoppingCartDto;

public class ShoppingCartResponseDto {
    
    private Long id;

    private List<ShoppingCartProductResponseDto> products;

    private boolean complete;

    public ShoppingCartResponseDto() {
    }

    public ShoppingCartResponseDto(Long id, List<ShoppingCartProductResponseDto> products, boolean complete) {
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

    public List<ShoppingCartProductResponseDto> getProducts() {
        return this.products;
    }

    public void setProducts(List<ShoppingCartProductResponseDto> products) {
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

        List<ShoppingCartProductResponseDto> products = fullShoppingCart.getProducts()
            .stream()
            .map(product -> new ShoppingCartProductResponseDto(
                    product.getProdId(), 
                    product.getQuantity()))
            .collect(Collectors.toList());

        return new ShoppingCartResponseDto(
            fullShoppingCart.getId(), 
            products, 
            fullShoppingCart.isCompleted());
    }
}
