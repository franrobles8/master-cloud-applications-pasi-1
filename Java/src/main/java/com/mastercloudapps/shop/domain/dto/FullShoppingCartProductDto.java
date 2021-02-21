package com.mastercloudapps.shop.domain.dto;

public class FullShoppingCartProductDto {

    private FullShoppingCartDto shoppingCart;

    private FullProductDto product;

    private Integer quantity;

    public FullShoppingCartProductDto() {
    }


    public FullShoppingCartProductDto(FullShoppingCartDto shoppingCart, FullProductDto product, Integer quantity) {
        this.shoppingCart = shoppingCart;
        this.product = product;
        this.quantity = quantity;
    }

    public FullShoppingCartDto getShoppingCart() {
        return this.shoppingCart;
    }

    public void setShoppingCart(FullShoppingCartDto shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public FullProductDto getProduct() {
        return this.product;
    }

    public void setProduct(FullProductDto product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }    

}
