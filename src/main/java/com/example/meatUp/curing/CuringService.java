package com.example.meatUp.curing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuringService {

    private final CuringMongoRepository curingMongoRepository;

    @Autowired
    public CuringService(CuringMongoRepository curingMongoRepository) {
        this.curingMongoRepository = curingMongoRepository;
    }

    public Optional<Curing> findById(String id) {
        return curingMongoRepository.findById(id);
    }

    public List<Curing> findAll() {
        return curingMongoRepository.findAll();
    }

    public void deleteCuring(String curingId) {
        curingMongoRepository.deleteById(curingId);
    }

    public void save(Curing curing) {
        try {
            curingMongoRepository.save(curing);
        }
        catch (Exception e)   {
            System.out.println("Save curing service error " + e);
        }
    }

    public void updateCuring(String curingId,Curing curing) {
        Optional<Curing> foundCuring = curingMongoRepository.findById(curingId);
        try {
            try {
                if (foundCuring.isPresent()) {
                    System.out.println("updating the curing object");
                    curing.setId(foundCuring.get().getId());
                    curingMongoRepository.save(curing);
                } else throw new Exception("No valid id found");
            } catch (Exception e) {
                System.out.println("Updating curing failed with error: " + e);
                // LOG.ERROR("Updating curing failed with error: " + e));
            }
        }
        catch (Exception e)   {
            System.out.println("Update curing repository error " + e);
        }
    }


}
