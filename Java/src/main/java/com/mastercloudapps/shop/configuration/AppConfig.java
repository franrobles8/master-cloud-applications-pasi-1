package com.mastercloudapps.shop.configuration;

import com.mastercloudapps.shop.domain.port.ProductUseCase;
import com.mastercloudapps.shop.domain.port.ProductUseCaseImpl;
import com.mastercloudapps.shop.domain.repository.ProductRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    
    @Bean
    public ProductUseCase productUseCase(ProductRepository productRepository) {
        return new ProductUseCaseImpl(productRepository);
    }

}
