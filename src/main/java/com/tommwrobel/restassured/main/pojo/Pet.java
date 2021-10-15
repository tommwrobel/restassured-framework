package com.tommwrobel.restassured.main.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Pet {

    private Integer id;
    private Category category;
    private String name;
    private List<String> photoUrls = null;
    private List<Tag> tags = null;
    private String status;
}
