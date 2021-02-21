package com.mastercloudapps.shop.domain.repository;

import java.util.Optional;

import com.mastercloudapps.shop.domain.dto.FullShoppingCartDto;
import com.mastercloudapps.shop.domain.dto.ShoppingCartDto;

public interface ShoppingCartRepository {
    
    FullShoppingCartDto save(ShoppingCartDto shoppingCart);

    Optional<FullShoppingCartDto> findShoppingCartById(Long id);

    Optional<FullShoppingCartDto> finishShoppingCartById(Long id);

    Optional<FullShoppingCartDto> deleteShoppingCartById(Long id);

    Optional<FullShoppingCartDto> addProductToShoppingCartByIds(Long cartId, Long prodId, Integer quantity);

    Optional<FullShoppingCartDto> deleteProductFromShoppingCart(Long cartId, Long prodId);

}
