
package com.example.smartpantrymanager;

import java.util.ArrayList;

// Represents one recipe in the Smart Pantry Manager.
public class Recipe {

    // Unique identification number for the recipe.
    private int id;

    // Name of the recipe.
    private String name;

    // Ingredients required, including their quantities and units.
    private ArrayList<RecipeIngredient> requiredIngredients;

    // Instructions explaining how to prepare the recipe.
    private String preparationSteps;

    // Creates a recipe with ingredient quantities.
    public Recipe(
            int id,
            String name,
            ArrayList<RecipeIngredient> requiredIngredients,
            String preparationSteps) {

        this.id = id;
        this.name = name;
        this.requiredIngredients = requiredIngredients;
        this.preparationSteps = preparationSteps;
    }

    // Returns the recipe ID.
    public int getId() {
        return id;
    }

    // Updates the recipe ID.
    public void setId(int id) {
        this.id = id;
    }

    // Returns the recipe name.
    public String getName() {
        return name;
    }

    // Updates the recipe name.
    public void setName(String name) {
        this.name = name;
    }

    // Returns the ingredients and amounts needed.
    public ArrayList<RecipeIngredient> getRequiredIngredients() {
        return requiredIngredients;
    }

    // Updates the recipe's ingredient requirements.
    public void setRequiredIngredients(
            ArrayList<RecipeIngredient> requiredIngredients) {
        this.requiredIngredients = requiredIngredients;
    }

    // Returns the preparation instructions.
    public String getPreparationSteps() {
        return preparationSteps;
    }

    // Updates the preparation instructions.
    public void setPreparationSteps(String preparationSteps) {
        this.preparationSteps = preparationSteps;
    }

    // Displays the recipe name when shown as text.
    @Override
    public String toString() {
        return name;
    }
}
