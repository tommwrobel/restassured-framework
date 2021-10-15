package com.tommwrobel.restassured.main.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Builder
public class ApiResponse {

    private Integer code;
    private String type;
    private String message;
    private Map<String, Object> additionalProperties = new HashMap<>();
}
