package com.mastercloudapps.shop.domain.repository;

import java.util.List;
import java.util.Optional;

import com.mastercloudapps.shop.domain.dto.FullProductDto;
import com.mastercloudapps.shop.domain.dto.ProductDto;

public interface ProductRepository {
    
    List<FullProductDto> findAllProducts();

    FullProductDto save(ProductDto product);

    Optional<FullProductDto> findProductById(Long id);

    void deleteProductById(Long id);

}
