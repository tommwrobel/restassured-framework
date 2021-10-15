package com.tommwrobel.restassured.main.pojo;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    private Integer id;
    private String name;
}
