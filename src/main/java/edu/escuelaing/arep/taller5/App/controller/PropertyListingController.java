package edu.escuelaing.arep.taller5.App.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.escuelaing.arep.taller5.App.expection.PropertyListingException;
import edu.escuelaing.arep.taller5.App.model.Property;
import edu.escuelaing.arep.taller5.App.services.PropertyListingServices;

@RestController
@RequestMapping("/api")
public class PropertyListingController {

    private static final String ERROR_KEY = "error";
    private PropertyListingServices propertyListingService;

    @Autowired
    public PropertyListingController(PropertyListingServices propertyListingServices) {
        this.propertyListingService = propertyListingServices;
    }

    @GetMapping("/property/{id}")
    public ResponseEntity<Object> getPropertyById(@PathVariable Long id) {
        Property property;
        try {
            property = propertyListingService.getPropertyById(id);
            return new ResponseEntity<>(property, HttpStatus.OK);
        } catch (PropertyListingException e) {
            return new ResponseEntity<>(Map.of(ERROR_KEY, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/property")
    public ResponseEntity<Object> getProperties() {
        List<Property> properties;
        properties = propertyListingService.getProperties();
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

}