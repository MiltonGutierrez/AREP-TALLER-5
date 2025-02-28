package edu.escuelaing.arep.taller5.App.services;

import java.util.List;
import java.util.Map;

import edu.escuelaing.arep.taller5.App.expection.PropertyListingException;
import edu.escuelaing.arep.taller5.App.model.Property;

public class PropertyListingServicesImpl implements PropertyListingServices{

    @Override
    public List<Property> getProperties()  {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProperties'");
    }

    @Override
    public Property getPropertyById(Long id) throws PropertyListingException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPropertyById'");
    }

    @Override
    public Property updateProperty(Long id, Map<String, String> values) throws PropertyListingException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateProperty'");
    }

    @Override
    public Property deleteProperty(Long id) throws PropertyListingException{
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteProperty'");
    }
    
}
