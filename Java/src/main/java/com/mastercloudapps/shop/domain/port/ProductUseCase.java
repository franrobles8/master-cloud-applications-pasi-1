package com.mastercloudapps.shop.domain.port;

import java.util.Collection;
import java.util.Optional;

import com.mastercloudapps.shop.domain.dto.FullProductDto;
import com.mastercloudapps.shop.domain.dto.ProductDto;

public interface ProductUseCase {
    
    public Collection<FullProductDto> findAllProducts();

    public FullProductDto createProduct(ProductDto product);

    public Optional<FullProductDto> findProductById(Long id);

    public void deleteProductById(Long id);

}
