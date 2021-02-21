package com.mastercloudapps.shop.domain.dto;

import java.util.List;

public class FullShoppingCartDto {
    
    private Long id;

    private List<FullShoppingCartProductDto> products;

    private boolean completed;

    public FullShoppingCartDto() {
    }

    public FullShoppingCartDto(Long id, List<FullShoppingCartProductDto> products, boolean completed) {
        this.id = id;
        this.products = products;
        this.completed = completed;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<FullShoppingCartProductDto> getProducts() {
        return this.products;
    }

    public void setProducts(List<FullShoppingCartProductDto> products) {
        this.products = products;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public boolean getCompleted() {
        return this.completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}
