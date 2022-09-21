package com.example.bvnvalidator.repository;

import com.example.bvnvalidator.model.Bvn;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BvnRepository extends MongoRepository<Bvn, Long> {
    Bvn findBvnByBvn(String bvn);
}
