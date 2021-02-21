package com.mastercloudapps.shop.domain.repository;

import com.mastercloudapps.shop.infrastructure.model.ShoppingCartProduct;
import com.mastercloudapps.shop.infrastructure.model.ShoppingCartProductId;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartProductJpaRepository extends JpaRepository<ShoppingCartProduct, ShoppingCartProductId> {
    
}
