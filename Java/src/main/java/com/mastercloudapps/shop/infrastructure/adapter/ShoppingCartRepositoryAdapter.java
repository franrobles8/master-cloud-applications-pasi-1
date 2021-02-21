package com.mastercloudapps.shop.infrastructure.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.mastercloudapps.shop.domain.dto.FullProductDto;
import com.mastercloudapps.shop.domain.dto.FullShoppingCartDto;
import com.mastercloudapps.shop.domain.dto.FullShoppingCartProductDto;
import com.mastercloudapps.shop.domain.dto.ShoppingCartDto;
import com.mastercloudapps.shop.domain.repository.ShoppingCartRepository;
import com.mastercloudapps.shop.infrastructure.model.ProductEntity;
import com.mastercloudapps.shop.infrastructure.model.ShoppingCartEntity;
import com.mastercloudapps.shop.infrastructure.model.ShoppingCartProduct;
import com.mastercloudapps.shop.infrastructure.repository.ShoppingCartJpaRepository;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartRepositoryAdapter implements ShoppingCartRepository {

    private ModelMapper mapper;
    private ShoppingCartJpaRepository shoppingCartJpaRepository;

    public ShoppingCartRepositoryAdapter(ShoppingCartJpaRepository shoppingCartJpaRepository, ModelMapper mapper) {
        this.shoppingCartJpaRepository = shoppingCartJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public FullShoppingCartDto save(ShoppingCartDto shoppingCartDto) {
        ShoppingCartEntity shoppingCart = mapper.map(shoppingCartDto, ShoppingCartEntity.class);
        ShoppingCartEntity savedShoppingCartEntity = shoppingCartJpaRepository.save(shoppingCart);
        return mapper.map(savedShoppingCartEntity, FullShoppingCartDto.class);
    }

    @Override
    public Optional<FullShoppingCartDto> findShoppingCartById(Long id) {
        Optional<ShoppingCartEntity> shoppingCartEntity = shoppingCartJpaRepository.findById(id);
        return shoppingCartEntity.map(ShoppingCartRepositoryAdapter::mapToFullShoppingCartDto);
    }

    @Override
    public Optional<FullShoppingCartDto> finishShoppingCartById(Long id) {
        Optional<ShoppingCartEntity> shoppingCartEntity = shoppingCartJpaRepository.findById(id);
        if(shoppingCartEntity.isPresent()) {
            ShoppingCartEntity shoppingCart = shoppingCartEntity.get();
            shoppingCart.setCompleted(true);
            shoppingCartJpaRepository.save(shoppingCart);
            //return savedShoppingCart.map(ShoppingCartRepositoryAdapter::mapToFullShoppingCartDto);
        }
        return shoppingCartEntity.map(ShoppingCartRepositoryAdapter::mapToFullShoppingCartDto);
    }

    private static FullShoppingCartDto mapToFullShoppingCartDto(ShoppingCartEntity shoppingCart) {
        return new FullShoppingCartDto(
            shoppingCart.getId(), 
            shoppingCart.getProducts()
                .stream()
                .map(p -> new ModelMapper().map(p, FullShoppingCartProductDto.class))
                .collect(Collectors.toList()), 
            shoppingCart.isCompleted()    
        );
    }
    
}
