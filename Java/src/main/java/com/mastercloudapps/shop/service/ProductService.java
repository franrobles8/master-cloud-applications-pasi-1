package com.mastercloudapps.shop.service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import com.mastercloudapps.shop.controller.dto.request.ProductRequestDto;
import com.mastercloudapps.shop.controller.dto.response.ProductResponseDto;
import com.mastercloudapps.shop.domain.dto.FullProductDto;
import com.mastercloudapps.shop.domain.dto.ProductDto;
import com.mastercloudapps.shop.domain.port.ProductUseCase;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductUseCase productUseCase;

    public ProductService(ProductUseCase productUseCase) {
        this.productUseCase = productUseCase;
    }
    
    public Collection<ProductResponseDto> findAll() {
        
        return this.productUseCase
                .findAllProducts()
                .stream()
                .map(ProductResponseDto::fromFullProductDto)
                .collect(Collectors.toList());

    }

    public FullProductDto save(ProductRequestDto product) {
        
        ProductDto productDto = new ProductDto(product.getName());

        return this.productUseCase.createProduct(productDto);
    }

    public Optional<ProductResponseDto> findById(Long id) {
        return this.productUseCase.findProductById(id).map(ProductResponseDto::fromFullProductDto);
    }

    public void deleteById(Long id) {
        this.productUseCase.deleteProductById(id);
    }

}
