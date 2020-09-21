package com.example.meatUp.curing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(CuringController.class)
public class CuringControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CuringService curingService;


    List<Curing> curings = new ArrayList<>();
    Curing bacon = new Curing("1", MeatCut.BACON, 300, LocalDate.of(2020, 9, 18));
    Curing pancetta = new Curing("2", MeatCut.PANCETTA, 500, LocalDate.of(2020, 9, 19));

    ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @BeforeEach
    void setMockOutput() {
        curings.add(bacon);
        curings.add(pancetta);
    }

    @AfterEach
    public void deleteList() {
        curings.clear();
    }

    @Test
    void findAll() throws Exception {
        when(curingService.findAll()).thenReturn(curings);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/curing"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("""
                        [{"id":"1","meatCut":"BACON","startDate":"2020-09-18","endDate":null,"daysOfAging":0,"startWeightInGrams":300,"finalWeightInGrams":0,"cuttingsInGrams":0,"weightOfSteaks":0,"waterlossPersentage":0.0},{"id":"2","meatCut":"PANCETTA","startDate":"2020-09-19","endDate":null,"daysOfAging":0,"startWeightInGrams":500,"finalWeightInGrams":0,"cuttingsInGrams":0,"weightOfSteaks":0,"waterlossPersentage":0.0}]"""
                ));
    }

    @Test
    void getCuringById() throws Exception {
        when(curingService.findById(any())).thenReturn(java.util.Optional.of(bacon));
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/curing/{curingId}", "1"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.meatCut").value("BACON"))
                .andExpect(jsonPath("$.id").value("1"));
    }
}
