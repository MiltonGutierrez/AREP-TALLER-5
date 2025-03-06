package edu.escuelaing.arep.taller5.app.controller;


import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface PropertyListingController {
    ResponseEntity<Object> getPropertyById(Long id);

    ResponseEntity<Object> getProperties();

    ResponseEntity<Object> updateProperty(Long id, Map<String, String> queryParams);

    ResponseEntity<Object> deleteProperty(Long id);

}
