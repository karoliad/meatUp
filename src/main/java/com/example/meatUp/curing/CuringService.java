package com.example.meatUp.curing;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CuringService {

    private final CuringMongoRepository curingMongoRepository;
    private final Logger LOG = LogManager.getLogger();

    @Autowired
    public CuringService(CuringMongoRepository curingMongoRepository) {
        this.curingMongoRepository = curingMongoRepository;
    }

    public Optional<Curing> findById(String id) {
        try{
            Optional<Curing> curing = curingMongoRepository.findById(id);
            if(curing.isPresent()) {
                LOG.info("Successfully found curing with id " + id);
                return curing;
            }return Optional.empty();
        }catch (Exception e){
            LOG.error("Failed to find curing with id "+ id +" "+ e);
            return Optional.empty();
        }
    }

    public List<Curing> findAll() {
        try{
            LOG.info("Successfully found all curings");
            return curingMongoRepository.findAll();
        }
        catch (Exception e){
             LOG.error("Failed to find all curings "+ e);
             return new ArrayList<>();
        }

    }

    public void deleteCuring(String curingId) {
        try {
            curingMongoRepository.deleteById(curingId);
            LOG.info("Successfully deleted curing with id "+ curingId);
        }
        catch (Exception e){
            LOG.error("Failed to delete curing with id "+ curingId +" "+ e);
        }
    }

    public void save(Curing curing) {
        try {
            curingMongoRepository.save(curing);
            LOG.info("Successfully saved the curing object with id: "+ curing.getId());
        }
        catch (Exception e)   {
            LOG.error("Failed to save curing service error " + e);
        }
    }

    public void updateCuring(String curingId,Curing curing) {
        Optional<Curing> foundCuring = curingMongoRepository.findById(curingId);
        try {
            try {
                if (foundCuring.isPresent()) {
                    curing.setId(foundCuring.get().getId());
                    curingMongoRepository.save(curing);
                    LOG.info("Successfully updated the curing object with curingId: "+ curingId);
                } else throw new Exception("No valid id found " + curingId);
            } catch (Exception e) {
                LOG.error("Failed to update curing of curingId " + curingId + " with error: " + e);
            }
        }
        catch (Exception e)   {
            LOG.error("Failed to connect with repository in updateCuring " + e);
        }
    }
}
