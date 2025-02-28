package edu.escuelaing.arep.taller5.App.services;

import java.util.List;
import java.util.Map;

import edu.escuelaing.arep.taller5.App.expection.PropertyListingException;
import edu.escuelaing.arep.taller5.App.model.Property;

public interface PropertyListingServices {

    List<Property> getProperties();

    Property getPropertyById(Long id) throws PropertyListingException;

    Property updateProperty(Long id, Map<String, String> values) throws PropertyListingException;

    Property deleteProperty(Long id) throws PropertyListingException;
    
}