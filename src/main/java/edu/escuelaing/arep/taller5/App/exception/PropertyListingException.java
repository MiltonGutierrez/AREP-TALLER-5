package edu.escuelaing.arep.taller5.App.exception;

public class PropertyListingException extends Exception{

    public static final String PROPERTY_NOT_FOUND = "Property not found";

    public PropertyListingException(String message){
        super(message);
    }
    
}
