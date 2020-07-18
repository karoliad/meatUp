package com.example.meatUp.curing;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CuringController {

    //private final CuringMongoRepository curingMongoRepository;

    //@Autowired
    //public CuringController (CuringMongoRepository curingService){
        //this.curingMongoRepository = curingService;
    //}

    @GetMapping("/")
    public void hello(){
        System.out.println("hello");
    }

    @GetMapping("/test")
    public void test(){
        System.out.println("jeg står i curingController - test");

    }

    //@GetMapping("/getall")
    //public List<Curing> printAllMeats(){
      //  System.out.println("jeg står i curingController - getAll");
     //   System.out.println(curingMongoRepository.findAll());
       // return curingMongoRepository.findAll();

    //}


 /*   @PostMapping
    public void addCuring(Curing curing){
        curingService.addCuring(curing);
    }

  */
}
