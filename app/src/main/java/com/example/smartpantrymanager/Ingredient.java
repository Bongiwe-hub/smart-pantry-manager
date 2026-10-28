
package com.example.smartpantrymanager;

import java.util.Locale;

// Represents one ingredient stored in the user's pantry.
public class Ingredient {

    // Unique database identifier for this ingredient.
    private int id;

    // Ingredient name, for example "Tomato".
    private String name;

    // Amount available in the pantry.
    private double quantity;

    // Measurement unit, such as "grams", "ml", or "pieces".
    private String unit;

    // Expiry date, if the user has provided one.
    private String expiryDate;

    // Creates an ingredient with all its details.
    public Ingredient(int id, String name, double quantity,
                      String unit, String expiryDate) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.expiryDate = expiryDate;
    }

    // Returns the ingredient's ID.
    public int getId() {
        return id;
    }

    // Updates the ingredient's ID, for example after database insertion.
    public void setId(int id) {
        this.id = id;
    }

    // Returns the ingredient's name.
    public String getName() {
        return name;
    }

    // Updates the ingredient's name.
    public void setName(String name) {
        this.name = name;
    }

    // Returns the available quantity.
    public double getQuantity() {
        return quantity;
    }

    // Updates the available quantity.
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    // Returns the measurement unit.
    public String getUnit() {
        return unit;
    }

    // Updates the measurement unit.
    public void setUnit(String unit) {
        this.unit = unit;
    }

    // Returns the expiry date.
    public String getExpiryDate() {
        return expiryDate;
    }

    // Updates the expiry date.
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    // Checks whether another name refers to this same ingredient.
    // For example, "Tomato" and "tomato" will match.
    public boolean hasSameName(String otherName) {
        return otherName != null
                && name != null
                && name.trim().toLowerCase(Locale.ROOT)
                .equals(otherName.trim().toLowerCase(Locale.ROOT));
    }

    // Returns a readable description for the pantry list.
    // Displays the ingredient neatly in the pantry list.
    @Override
    public String toString() {

        // Display whole quantities without unnecessary decimal places.
        String formattedQuantity;

        if (quantity == Math.floor(quantity)) {
            formattedQuantity = String.valueOf((long) quantity);
        } else {
            formattedQuantity = String.valueOf(quantity);
        }

        return name + " - " + formattedQuantity + " " + unit;
    }

}
