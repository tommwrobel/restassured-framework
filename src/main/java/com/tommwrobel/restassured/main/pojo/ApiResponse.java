package com.tommwrobel.restassured.main.pojo;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

    private Integer code;
    private String type;
    private String message;
}
