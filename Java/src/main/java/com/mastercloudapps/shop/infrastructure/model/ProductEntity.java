package com.mastercloudapps.shop.infrastructure.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ProductEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "prod", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShoppingCartProduct> shoppingCarts = new ArrayList<>();

    public ProductEntity() {
    }

    public ProductEntity(String name) {
        this.name = name;
    }

    public ProductEntity(Long id, String name) {
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

    public List<ShoppingCartProduct> getShoppingCarts() {
        return this.shoppingCarts;
    }

    public void setShoppingCarts(List<ShoppingCartProduct> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + this.getId() + "'" +
            ", name='" + this.getName() + "'" +
            "}";
    }

}
