package com.tommwrobel.restassured.main.pojo;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tag {

    private Integer id;
    private String name;
}
