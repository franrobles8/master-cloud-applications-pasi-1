package com.mastercloudapps.shop.infrastructure.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ShoppingCartProductId implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long shoppingCartId;
    private Long productId;

    public ShoppingCartProductId() {
    }

    public ShoppingCartProductId(Long shoppingCartId, Long productId) {
        this.shoppingCartId = shoppingCartId;
        this.productId = productId;
    }

    public Long getShoppingCartId() {
        return this.shoppingCartId;
    }

    public void setShoppingCartId(Long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public Long getProductId() {
        return this.productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

}
