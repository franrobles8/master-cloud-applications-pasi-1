package com.mastercloudapps.shop.domain.dto;

import java.util.List;

public class ShoppingCartDto {

    private Long id;

    private List<FullShoppingCartProductDto> products;

    private boolean complete;

    public ShoppingCartDto () {
    }

    public ShoppingCartDto (List<FullShoppingCartProductDto> products) {
        this.products = products;
    }

    public ShoppingCartDto (Long id, List<FullShoppingCartProductDto> products, boolean complete) {
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

    public List<FullShoppingCartProductDto> getProducts() {
        return this.products;
    }

    public void setProducts(List<FullShoppingCartProductDto> products) {
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

}
