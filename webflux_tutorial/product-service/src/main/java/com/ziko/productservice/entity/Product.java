package com.ziko.productservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 08 Nov, 2023
 */

@Data
public class Product {

    @Id
    private String id;
    private String description;
    private Integer price;

}
