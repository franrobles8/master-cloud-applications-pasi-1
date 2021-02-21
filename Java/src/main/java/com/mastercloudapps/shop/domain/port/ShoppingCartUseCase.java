package com.mastercloudapps.shop.domain.port;

import java.util.Optional;

import com.mastercloudapps.shop.domain.dto.FullShoppingCartDto;

public interface ShoppingCartUseCase {
    
    public FullShoppingCartDto createShoppingCart();

    public Optional<FullShoppingCartDto> findShoppingCartById(Long id);

    public Optional<FullShoppingCartDto> finishShoppingCartById(Long id);

}
