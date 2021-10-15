package com.tommwrobel.restassured.main.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Tag {

    private Integer id;
    private String name;
}
