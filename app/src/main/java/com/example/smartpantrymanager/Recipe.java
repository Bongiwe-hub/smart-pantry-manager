
package com.example.smartpantrymanager;

import java.util.ArrayList;

// Represents one recipe in the Smart Pantry Manager.
public class Recipe {

    // Unique identification number for the recipe.
    private int id;

    // Name of the recipe, for example "Vegetable Omelette".
    private String name;

    // Names of the ingredients needed to prepare the recipe.
    private ArrayList<String> requiredIngredients;

    // Instructions explaining how to prepare the recipe.
    private String preparationSteps;

    // Constructor used to create a new recipe.
    public Recipe(
            int id,
            String name,
            ArrayList<String> requiredIngredients,
            String preparationSteps) {

        this.id = id;
        this.name = name;
        this.requiredIngredients = requiredIngredients;
        this.preparationSteps = preparationSteps;
    }

    // Returns the recipe's ID.
    public int getId() {
        return id;
    }

    // Updates the recipe's ID.
    public void setId(int id) {
        this.id = id;
    }

    // Returns the recipe's name.
    public String getName() {
        return name;
    }

    // Updates the recipe's name.
    public void setName(String name) {
        this.name = name;
    }

    // Returns the list of required ingredients.
    public ArrayList<String> getRequiredIngredients() {
        return requiredIngredients;
    }

    // Updates the required ingredients.
    public void setRequiredIngredients(
            ArrayList<String> requiredIngredients) {
        this.requiredIngredients = requiredIngredients;
    }

    // Returns the recipe preparation instructions.
    public String getPreparationSteps() {
        return preparationSteps;
    }

    // Updates the preparation instructions.
    public void setPreparationSteps(String preparationSteps) {
        this.preparationSteps = preparationSteps;
    }

    // Displays the recipe name when the object is shown as text.
    @Override
    public String toString() {
        return name;
    }
}
