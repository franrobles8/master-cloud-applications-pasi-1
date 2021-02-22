package com.mastercloudapps.shop.domain.dto;

public class FullShoppingCartProductDto {

    private Long shopId;

    private Long prodId;

    private Integer quantity;

    public FullShoppingCartProductDto() {
    }

    public FullShoppingCartProductDto(Long shopId, Long prodId, Integer quantity) {
        this.shopId = shopId;
        this.prodId = prodId;
        this.quantity = quantity;
    }

    public Long getShopId() {
        return this.shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getProdId() {
        return this.prodId;
    }

    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
