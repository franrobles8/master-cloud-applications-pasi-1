package com.mastercloudapps.shop.configuration;

import com.mastercloudapps.shop.domain.port.ProductUseCase;
import com.mastercloudapps.shop.domain.port.ProductUseCaseImpl;
import com.mastercloudapps.shop.domain.port.ShoppingCartUseCase;
import com.mastercloudapps.shop.domain.port.ShoppingCartUseCaseImpl;
import com.mastercloudapps.shop.domain.repository.ProductRepository;
import com.mastercloudapps.shop.domain.repository.ShoppingCartRepository;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    
    @Bean
    public ProductUseCase productUseCase(ProductRepository productRepository) {
        return new ProductUseCaseImpl(productRepository);
    }

    @Bean
    public ShoppingCartUseCase shoppingCartUseCase(ShoppingCartRepository shoppingCartRepository) {
        return new ShoppingCartUseCaseImpl(shoppingCartRepository);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
