package com.mastercloudapps.shop.domain.port;

import java.util.Collection;
import java.util.Optional;

import com.mastercloudapps.shop.domain.dto.FullProductDto;
import com.mastercloudapps.shop.domain.dto.ProductDto;
import com.mastercloudapps.shop.domain.repository.ProductRepository;

public class ProductUseCaseImpl implements ProductUseCase {

    ProductRepository productRepository;

    public ProductUseCaseImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Collection<FullProductDto> findAllProducts() {
        return productRepository.findAllProducts();
    }

    @Override
    public FullProductDto createProduct(ProductDto product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<FullProductDto> findProductById(Long id) {
        return productRepository.findProductById(id);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteProductById(id);
    }
    
}
