package com.example.bvnvalidator.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Document(collection="bvn")
@Setter @Getter
@Data @NoArgsConstructor @AllArgsConstructor
public class Bvn {

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    private Long id;
    private String bvn;
}
