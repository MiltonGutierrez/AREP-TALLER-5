package edu.escuelaing.arep.taller5.App.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String propertyName;
    private String description;
    private PropertyListingState listingState;
    private PropertyState propertyState;
    private LocalDate listingDate;


    public Property(){}

    public Property(String propertyName, String description, PropertyListingState listingState, PropertyState propertyState, LocalDate listingDate){
        this.propertyName = propertyName;
        this.description = description;
        this.listingState = listingState;
        this.propertyState = propertyState;
        this.listingDate = listingDate;
    
    }


    // Getter y Setter para propertyName
    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    // Getter y Setter para description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getter y Setter para listingState
    public PropertyListingState getListingState() {
        return listingState;
    }

    public void setListingState(PropertyListingState listingState) {
        this.listingState = listingState;
    }

    // Getter y Setter para propertyState
    public PropertyState getPropertyState() {
        return propertyState;
    }

    public void setPropertyState(PropertyState propertyState) {
        this.propertyState = propertyState;
    }

    // Getter y Setter para listingDate
    public LocalDate getListingDate() {
        return listingDate;
    }

    public void setListingDate(LocalDate listingDate) {
        this.listingDate = listingDate;
    }

}
