package com.example.bvnvalidator.service;

import com.example.bvnvalidator.dto.BvnInputDto;
import com.example.bvnvalidator.response.BvnResponse;

public interface BvnService {

    BvnResponse validate(BvnInputDto bvnInputDto);

    BvnResponse validateBvn(BvnInputDto bvnInputDto);
}
