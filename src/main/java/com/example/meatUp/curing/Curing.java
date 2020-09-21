package com.example.meatUp.curing;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

@Document
@Getter
@Setter
@ToString
public class Curing {

    @Id
    private String id = UUID.randomUUID().toString();
    private MeatCut meatCut;
    private LocalDate startDate;
    private LocalDate endDate;
    private int daysOfAging = 0;
    private int startWeightInGrams;
    private int finalWeightInGrams;
    private int cuttingsInGrams;
    private int weightOfSteaks;
    private double waterlossPersentage;
//    private ArrayList<String> otherInfo = new ArrayList<>();

    public Curing(){
        this.startDate = LocalDate.now();
    }

    public Curing(MeatCut meatCut, int startWeightInGrams) {
        this.meatCut = meatCut;
        this.startDate = LocalDate.now();
        this.startWeightInGrams = startWeightInGrams;
    }
    public Curing(String id,MeatCut meatCut, int startWeightInGrams, LocalDate startDate) {
        this.id = id;
        this.meatCut = meatCut;
        this.startWeightInGrams = startWeightInGrams;
        this.startDate = startDate;
    }


}
