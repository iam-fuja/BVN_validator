package com.example.bvnvalidator.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class BvnResponse {

    private byte[] image1;
    private byte[] image2;
    private String bvn;
    private String message;
    private Integer code;
}
