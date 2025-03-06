package edu.escuelaing.arep.taller5.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.escuelaing.arep.taller5.app.model.Property;

@Repository
public interface PropertyListingRepository extends CrudRepository<Property, Long>  {
    Property findById(long id);
}
