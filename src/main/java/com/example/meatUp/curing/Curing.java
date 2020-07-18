package com.example.meatUp.curing;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

@Document
@Getter
@Setter
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
    private ArrayList<String> otherInfo = new ArrayList<>();



    public Curing(MeatCut meatCut, int startWeightInGrams) {
        this.meatCut = meatCut;
        this.startDate = LocalDate.now();
        this.startWeightInGrams = startWeightInGrams;
    }

    @Override
    public String toString() {
        return "Curing{" +
                "id='" + id + '\'' +
                ", meatCut=" + meatCut +
                ", startDate=" + startDate +
                '}';
    }
}
