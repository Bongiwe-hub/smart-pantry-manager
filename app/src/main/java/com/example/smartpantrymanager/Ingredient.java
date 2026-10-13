package com.example.smartpantrymanager;


package com.example.smartpantrymanager;

// Represents one ingredient in the user's pantry.
public class Ingredient {

    // Unique identifier for the ingredient.
    private int id;

    // Name of the ingredient, for example "Tomato".
    private String name;

    // Quantity available in the pantry.
    private double quantity;

    // Unit used to measure the quantity, such as "grams".
    private String unit;

    // Expiry date, if known.
    private String expiryDate;

    // Constructor: initializes an ingredient object.
    public Ingredient(int id, String name, double quantity,
                      String unit, String expiryDate) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.expiryDate = expiryDate;
    }

    // Gets the ingredient's unique identifier.
    public int getId() {
        return id;
    }

    // Gets the ingredient's name.
    public String getName() {
        return name;
    }

    // Changes the ingredient's name.
    public void setName(String name) {
        this.name = name;
    }

    // Gets the available quantity.
    public double getQuantity() {
        return quantity;
    }

    // Changes the available quantity.
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    // Gets the measurement unit.
    public String getUnit() {
        return unit;
    }

    // Changes the measurement unit.
    public void setUnit(String unit) {
        this.unit = unit;
    }

    // Gets the expiry date.
    public String getExpiryDate() {
        return expiryDate;
    }

    // Changes the expiry date.
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    // Returns a readable description of the ingredient.
    @Override
    public String toString() {
        return name + " - " + quantity + " " + unit;
    }
}

