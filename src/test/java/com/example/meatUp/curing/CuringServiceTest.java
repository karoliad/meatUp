package com.example.meatUp.curing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CuringServiceTest {

    @InjectMocks
    CuringService curingService;

    @Mock
    CuringMongoRepository mockRepository;

    @BeforeEach
    void setMockOutput() {
        Curing bacon = new Curing(MeatCut.BACON, 300);
        when(mockRepository.findById(any())).thenReturn(java.util.Optional.of(bacon));
    }


    @Test
    void findById() {
        Optional<Curing> c = curingService.findById("2");
        System.out.println("HEI HIE: " + c);
    }

    @Test
    void findAll() {
    }

    @Test
    void deleteCuring() {
    }

    @Test
    void save() {
    }

    @Test
    void updateCuring() {
    }


}