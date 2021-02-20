package com.mastercloudapps.shop.domain.dto;

public class FullProductDto {
    
    private Long id;

    private String name;

    public FullProductDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + this.getId() + "'" +
            ", name='" + this.getName() + "'" +
            "}";
    }

}
