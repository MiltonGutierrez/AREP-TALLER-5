package edu.escuelaing.arep.taller5.app.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.escuelaing.arep.taller5.app.exception.PropertyListingException;
import edu.escuelaing.arep.taller5.app.model.Property;
import edu.escuelaing.arep.taller5.app.repository.PropertyListingRepository;


@Service
public class PropertyListingServicesImpl implements PropertyListingServices{

    private PropertyListingRepository repository;


    @Autowired
    public PropertyListingServicesImpl(PropertyListingRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Property> getProperties()  {
       return (List<Property>) repository.findAll();
    }

    @Override
    public Property getPropertyById(Long id) throws PropertyListingException {
        Optional<Property> propertyInDB = repository.findById(id);
        if(propertyInDB.isEmpty()){
            throw new PropertyListingException(PropertyListingException.PROPERTY_NOT_FOUND);
        }
        return propertyInDB.get();

    }

    @Override
    public Property updateProperty(Long id, Map<String, String> values) throws PropertyListingException {
        Property propertyInDB = getPropertyById(id);
        boolean updated = false;
        if(values.containsKey("address")){
            propertyInDB.setAddress(values.get("address"));
            updated = true;
        }
        if(values.containsKey("prize")){
            propertyInDB.setAddress(values.get("prize"));
            updated = true;
        }
        if(values.containsKey("size")){
            propertyInDB.setAddress(values.get("size"));
            updated = true;
        }
        if(values.containsKey("description")){
            propertyInDB.setAddress(values.get("description"));
            updated = true;
        }
        if(!updated){
            throw new PropertyListingException(PropertyListingException.PROPERTY_NOT_UPDATED);
        }
        return repository.save(propertyInDB);
    }

    @Override
    public Property deleteProperty(Long id) throws PropertyListingException{
        Property propertyInDB = getPropertyById(id);
        repository.deleteById(id);
        return propertyInDB;
    }
    
}
