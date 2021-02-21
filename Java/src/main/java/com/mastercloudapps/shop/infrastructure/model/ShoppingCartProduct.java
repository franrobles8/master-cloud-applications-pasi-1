package com.mastercloudapps.shop.infrastructure.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class ShoppingCartProduct {
    
    @EmbeddedId
    private ShoppingCartProductId id;

    @ManyToOne
    @MapsId("shoppingCartId")
    private ShoppingCartEntity shop;

    @ManyToOne
    @MapsId("productId")
    private ProductEntity prod;

    private Integer quantity;

    public ShoppingCartProduct() {
    }

    public ShoppingCartProduct(ShoppingCartEntity shop, ProductEntity prod, Integer quantity) {
        this.shop = shop;
        this.prod = prod;
        this.quantity = quantity;
        this.id = new ShoppingCartProductId(shop.getId(), prod.getId());
    }

    public ShoppingCartProductId getId() {
        return this.id;
    }

    public void setId(ShoppingCartProductId id) {
        this.id = id;
    }

    public ShoppingCartEntity getShop() {
        return this.shop;
    }

    public void setShop(ShoppingCartEntity shop) {
        this.shop = shop;
    }

    public ProductEntity getProd() {
        return this.prod;
    }

    public void setProd(ProductEntity prod) {
        this.prod = prod;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
