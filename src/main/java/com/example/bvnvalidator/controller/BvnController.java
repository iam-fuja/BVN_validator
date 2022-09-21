package com.example.bvnvalidator.controller;


import com.example.bvnvalidator.dto.BvnInputDto;
import com.example.bvnvalidator.service.BvnService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BvnController {

    private final BvnService bvnService;


    @PostMapping("/bvn-services/validate/wrapper")
    public ResponseEntity<?> validate(@RequestBody BvnInputDto bvnInputDto){
        return ResponseEntity.ok(bvnService.validate(bvnInputDto));
    }

    @GetMapping("/check-bvn-existence")
    public ResponseEntity<?> bvnExistence(@RequestParam String bvnInput){
        BvnInputDto bvnInputDto = new BvnInputDto();
        bvnInputDto.setBvn(bvnInput);
        return ResponseEntity.ok(bvnService.validateBvn(bvnInputDto));
    }
}
