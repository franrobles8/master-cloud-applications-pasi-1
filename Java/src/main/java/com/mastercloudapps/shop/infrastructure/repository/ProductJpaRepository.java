package com.mastercloudapps.shop.infrastructure.repository;

import com.mastercloudapps.shop.infrastructure.model.ProductEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {

}
