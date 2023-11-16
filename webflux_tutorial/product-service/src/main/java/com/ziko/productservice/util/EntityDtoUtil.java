package com.ziko.productservice.util;

import com.ziko.productservice.dto.ProductDto;
import com.ziko.productservice.entity.Product;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 08 Nov, 2023
 */

public class EntityDtoUtil {

    public static ProductDto toProductDto(Product product){
        return new ProductDto(product.getId(), product.getDescription(), product.getPrice());
    }

    public static Product toProduct(ProductDto dto){
        Product product = new Product();
        product.setId(dto.id());
        product.setDescription(dto.description());
        product.setPrice(dto.price());
        return product;
    }
}
