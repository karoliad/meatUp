package com.example.meatUp.curing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/curing")
public class CuringController {

    private final CuringService curingService;

    //TODO: lage gode tilbakemeldinger til bruker ved 400 error

    @Autowired
    public CuringController(CuringService curingService) {
        this.curingService = curingService;
    }

    @GetMapping(path="{curingId}")
    public ResponseEntity<Optional<Curing>> getCuringById(@PathVariable("curingId") String id){
        try{
            Optional<Curing> curing =  curingService.findById(id);
            System.out.println("Successfully handled getCuringById for curingId: " + id);
            return ResponseEntity.ok(curing);
        }
        catch (Exception e){
            System.out.println("Failed to handle get getCuringById for curingId: "+ id +", " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("{curingId}")
    public ResponseEntity<String> deleteCuring(@PathVariable("curingId") String curingId){
        try {
            curingService.deleteCuring(curingId);
            System.out.println("Successfully handled delete deleteCuring for curingId: " + curingId);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e){
            System.out.println("Failed to handle delete deleteCuring " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<String> addNewCuring(@RequestBody Curing curing){
        try {
            curingService.save(curing);
            System.out.println("Successfully handled post addNewCuring.");
            return ResponseEntity.noContent().build();
        }
        catch (Exception e){
            System.out.println("Failed to handle post addNewCuring " + e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping (path = "{curingId}")
    public ResponseEntity<String> updateCuring(@PathVariable("curingId") String curingId,
                                               @RequestBody Curing curing){
        try {
            curingService.updateCuring(curingId, curing);
            System.out.println("Successfully handled put updateCuring.");
            return ResponseEntity.noContent().build();
        }
        catch (Exception e){
            System.out.println("Failed to handle put updateCuring " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Curing>> findAll(){
        try{
        List<Curing> allCurings = curingService.findAll();
            System.out.println("Successfully handled get allCurings.");
            return ResponseEntity.ok(allCurings);

    }catch (Exception e){
            System.out.println("Failed to handle get allCurings " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


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
