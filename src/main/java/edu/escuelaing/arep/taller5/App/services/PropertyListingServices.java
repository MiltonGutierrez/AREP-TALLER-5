package edu.escuelaing.arep.taller5.app.services;

import java.util.List;
import java.util.Map;

import edu.escuelaing.arep.taller5.app.exception.PropertyListingException;
import edu.escuelaing.arep.taller5.app.model.Property;

public interface PropertyListingServices {

    List<Property> getProperties();

    Property getPropertyById(Long id) throws PropertyListingException;

    Property updateProperty(Long id, Map<String, String> queryParams) throws PropertyListingException;

    Property deleteProperty(Long id) throws PropertyListingException;
    
}