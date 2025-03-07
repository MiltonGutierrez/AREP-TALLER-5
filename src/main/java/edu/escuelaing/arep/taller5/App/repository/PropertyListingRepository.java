package edu.escuelaing.arep.taller5.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.escuelaing.arep.taller5.app.model.Property;

@Repository
public interface PropertyListingRepository extends CrudRepository<Property, Long>  {
    Property findById(long id);
    List<Property> findByPriceBetween(Double minPrice, Double maxPrice);
    List<Property> findBySizeBetween(Double minSize, Double maxSize);    
}
