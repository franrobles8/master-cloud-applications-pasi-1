package com.mastercloudapps.shop.infrastructure.repository;

import com.mastercloudapps.shop.infrastructure.model.ShoppingCartEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartJpaRepository extends JpaRepository<ShoppingCartEntity, Long> {
    
}
