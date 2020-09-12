package com.example.meatUp.curing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class CuringMongoRepositoryTest {

    @Autowired
    private CuringMongoRepository curingMongoRepository;

    @BeforeEach
    public void initRepo() {
        Curing testmeat = new Curing(MeatCut.BACON, 300);
        Curing testmeat2 = new Curing(MeatCut.PANCETTA, 30);
        curingMongoRepository.save(testmeat);
        curingMongoRepository.save(testmeat2);
    }

    @AfterEach
    public void deleteRepo(){
        curingMongoRepository.deleteAll();
    }

    //Test to see that we have a connection with the repository interface
    @Test
    public void saveCuringToDatabase() {
        // when
        List<Curing> found = curingMongoRepository.findAll();
        // then
        assertEquals(found.size(), 2);
    }

    @Test
    public void findAllBacon(){
        //when
        List<Curing> allTheBacon = curingMongoRepository.findByMeatCut(MeatCut.BACON);

        //then
        assertEquals(allTheBacon.size(), 1);

    }
}