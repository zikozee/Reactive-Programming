package com.ziko.productservice.dto;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 08 Nov, 2023
 */


public record ProductDto(String id, String description, Integer price) {
    public ProductDto(String description, Integer price) {
        this(null, description, price);
    }
}
