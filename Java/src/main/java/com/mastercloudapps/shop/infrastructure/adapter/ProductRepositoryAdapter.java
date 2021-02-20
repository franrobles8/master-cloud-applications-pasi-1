package com.mastercloudapps.shop.infrastructure.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.mastercloudapps.shop.domain.dto.FullProductDto;
import com.mastercloudapps.shop.domain.dto.ProductDto;
import com.mastercloudapps.shop.domain.repository.ProductRepository;
import com.mastercloudapps.shop.infrastructure.model.ProductEntity;
import com.mastercloudapps.shop.infrastructure.repository.ProductJpaRepository;

import org.springframework.stereotype.Component;

@Component
public class ProductRepositoryAdapter implements ProductRepository {

    private ProductJpaRepository productJpaRepository;

    public ProductRepositoryAdapter(ProductJpaRepository productJpaRepository) {
        this.productJpaRepository = productJpaRepository;
    }

    @Override
    public List<FullProductDto> findAllProducts() {

        List<ProductEntity> products = productJpaRepository.findAll();
        return products.stream().map(ProductRepositoryAdapter::mapToFullProductDto).collect(Collectors.toList());

    }

    @Override
    public FullProductDto save(ProductDto product) {

        ProductEntity productEntity = new ProductEntity(product.getName());
        ProductEntity savedProductEntity = productJpaRepository.save(productEntity);
        return mapToFullProductDto(savedProductEntity);

    }

    @Override
    public Optional<FullProductDto> findProductById(Long id) {
        Optional<ProductEntity> product = productJpaRepository.findById(id);
        return product.map(ProductRepositoryAdapter::mapToFullProductDto);
    }

    @Override
    public void deleteProductById(Long id) {
        productJpaRepository.deleteById(id);
    }

    private static FullProductDto mapToFullProductDto(ProductEntity product) {
        return new FullProductDto(product.getId(), product.getName());
    }
    
}
