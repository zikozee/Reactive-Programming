package com.reactive.first_lesson.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @author : Ezekiel Eromosei
 * @created : 01 Feb, 2022
 */

@Data
public class Product {

    @Id
    private int id;
    private String name;
}
