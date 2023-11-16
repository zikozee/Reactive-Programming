package com.ziko.userservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 13 Nov, 2023
 */

@Data
@Table(name = "users")
public class User {

    @Id
    private Integer id;
    private String name;
    private Integer balance;
}
