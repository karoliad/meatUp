package com.example.meatUp.curing;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CuringMongoRepository extends MongoRepository<Curing, String> {
    List<Curing> findByMeatCut(MeatCut meatCut);
}

