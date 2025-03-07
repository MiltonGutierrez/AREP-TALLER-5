package edu.escuelaing.arep.taller5.app.services;

import java.util.List;
import java.util.Map;

import edu.escuelaing.arep.taller5.app.exception.PropertyListingException;
import edu.escuelaing.arep.taller5.app.model.Property;

public interface PropertyListingServices {

    List<Property> getProperties();

    List<Property> getPropertiesByPriceRange(double min, double max) throws PropertyListingException;

    List<Property> getPropertiesBySizeRange(double min, double max) throws PropertyListingException;

    Property getPropertyById(Long id) throws PropertyListingException;

    Property updateProperty(Long id, Map<String, String> queryParams) throws PropertyListingException;

    Property deleteProperty(Long id) throws PropertyListingException;

    Property createProperty(Property property) throws PropertyListingException;

    
}