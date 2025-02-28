package edu.escuelaing.arep.taller5.App.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.escuelaing.arep.taller5.App.model.Property;

@Repository
public interface PropertyListingRepository extends CrudRepository<Property, Long>  {
    Property findById(long id);
}
