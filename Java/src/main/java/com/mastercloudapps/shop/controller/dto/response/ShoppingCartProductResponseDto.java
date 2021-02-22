package com.mastercloudapps.shop.controller.dto.response;

public class ShoppingCartProductResponseDto {
    
    Long id;

    Integer quantity;

    public ShoppingCartProductResponseDto() {
    }

    public ShoppingCartProductResponseDto(Long id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
