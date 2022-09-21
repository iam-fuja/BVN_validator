package com.example.bvnvalidator.dto;

public class BvnInputDto {

    private String bvn;


    public BvnInputDto(String bvn) {
        this.bvn = bvn;
    }

    public BvnInputDto() {
    }

    public String getBvn() {
        return bvn;
    }

    public void setBvn(String bvn) {
        this.bvn = bvn;
    }
}
