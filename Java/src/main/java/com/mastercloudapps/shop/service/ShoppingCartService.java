package com.mastercloudapps.shop.service;

import java.util.Optional;

import com.mastercloudapps.shop.controller.dto.response.ShoppingCartResponseDto;
import com.mastercloudapps.shop.domain.dto.FullShoppingCartDto;
import com.mastercloudapps.shop.domain.port.ShoppingCartUseCase;

import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {
    
    private ShoppingCartUseCase shoppingCartUseCase;

    public ShoppingCartService(ShoppingCartUseCase shoppingCartUseCase) {
        this.shoppingCartUseCase = shoppingCartUseCase;
    }

    public FullShoppingCartDto save() {
        return shoppingCartUseCase.createShoppingCart();
    }

    public Optional<ShoppingCartResponseDto> findById(Long id) {
        return shoppingCartUseCase.findShoppingCartById(id).map(ShoppingCartResponseDto::fromFullShoppingCartDto);
    }

    public Optional<ShoppingCartResponseDto> finishShoppingCart(Long id) {
        return shoppingCartUseCase.finishShoppingCartById(id).map(ShoppingCartResponseDto::fromFullShoppingCartDto);
    }

}
