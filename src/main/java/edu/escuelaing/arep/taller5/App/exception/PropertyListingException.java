package edu.escuelaing.arep.taller5.app.exception;

public class PropertyListingException extends Exception{

    public static final String PROPERTY_NOT_FOUND = "Property not found";
    public static final String PROPERTY_NOT_UPDATED = "Property could not be updated";

    public PropertyListingException(String message){
        super(message);
    }
    
}
