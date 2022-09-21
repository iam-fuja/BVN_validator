package com.example.bvnvalidator;

import com.example.bvnvalidator.repository.BvnRepository;
import com.example.bvnvalidator.response.BvnResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
//@EnableMongoRepositories
public class BvnValidatorApplication {

//    @Autowired
// BvnRepository bvnCollector;


    public static void main(String[] args) {
        SpringApplication.run(BvnValidatorApplication.class, args);
    }

}


