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
public class ShoppingCartEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShoppingCartProduct> products = new ArrayList<>();

    private boolean completed;

    public ShoppingCartEntity() {
    }

    public ShoppingCartEntity(List<ShoppingCartProduct> products) {
        this.products = products;
    }

    public ShoppingCartEntity(Long id, List<ShoppingCartProduct> products) {
        this.id = id;
        this.products = products;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ShoppingCartProduct> getProducts() {
        return this.products;
    }

    public void setProducts(List<ShoppingCartProduct> products) {
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

    @Override
    public String toString() {
        return "{" +
            " id='" + this.getId() + "'" +
            ", products='" + this.getProducts() + "'" +
            "}";
    }

}
