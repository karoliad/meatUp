package com.example.meatUp.curing;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/curing")
public class CuringController {

    private final CuringService curingService;
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public CuringController(CuringService curingService) {
        this.curingService = curingService;
    }

    @GetMapping(path="{curingId}")
    public Optional<Curing> getCuringById(@PathVariable("curingId") String id){
        return curingService.findById(id);
    }

    @DeleteMapping("{curingId}")
    public void deleteCuring( @PathVariable("curingId") String curingId){
        curingService.deleteCuring(curingId);
    }

    @PostMapping
    public void addNewCuring(@RequestBody Curing curing){
        curingService.save(curing);
    }

    // Handling JSON with jackson mapping (convert json to java)
    /*@PostMapping
    public void addNewCuring(@RequestBody String curing) throws JsonProcessingException {
        System.out.println(curing);
        Curing c = objectMapper.readValue(curing, Curing.class);
        System.out.println(c);
        curingService.save(c);

        if(c.getMeatCut().name() == null) {
            return  "name must be added";
        }
    }*/

    @PutMapping (path = "{curingId}")
    public void updateCuring(@PathVariable("curingId") String curingId,
                             @RequestBody Curing curing){
        curingService.updateCuring(curingId,curing);

    }

    @GetMapping
    public List<Curing> findAll(){
        return curingService.findAll();
    }


 /*   @PostMapping
    public void addCuring(Curing curing){
        curingService.addCuring(curing);
    }

  */


    @GetMapping("/dummy")
    public void createDummyData(){
        Curing test = new Curing(MeatCut.ENTRECÔTE, 3500);
        Curing test2 = new Curing(MeatCut.DUCKBREAST, 500);
        Curing test3 = new Curing(MeatCut.BACON, 600);

        curingService.save(test);
        curingService.save(test2);
        curingService.save(test3);
    }

}
