
package com.example.smartpantrymanager;

import java.util.ArrayList;

// Stores the 15 recipes available in the application.
public class RecipeData {

    // Creates and returns the complete recipe list.
    public static ArrayList<Recipe> getAllRecipes() {

        ArrayList<Recipe> recipes = new ArrayList<>();

        recipes.add(new Recipe(1, "Vegetable Omelette",
                ingredients(
                        item("eggs", 2, "items"),
                        item("tomato", 1, "items"),
                        item("onion", 0.5, "items")),
                steps("Beat the eggs.",
                        "Chop the tomato and onion.",
                        "Cook the vegetables in a pan.",
                        "Add the eggs and cook until firm.")));

        recipes.add(new Recipe(2, "Cheese Sandwich",
                ingredients(
                        item("bread", 2, "slices"),
                        item("cheese", 2, "slices"),
                        item("butter", 10, "grams")),
                steps("Spread butter on the bread.",
                        "Place cheese between the slices.",
                        "Close the sandwich and serve.")));

        recipes.add(new Recipe(3, "Tomato Pasta",
                ingredients(
                        item("pasta", 100, "grams"),
                        item("tomato", 2, "items"),
                        item("onion", 0.5, "items"),
                        item("garlic", 1, "cloves")),
                steps("Boil the pasta.",
                        "Chop the tomato, onion and garlic.",
                        "Cook the vegetables in a pan.",
                        "Mix the sauce with the drained pasta.")));

        recipes.add(new Recipe(4, "Chicken and Rice",
                ingredients(
                        item("chicken", 150, "grams"),
                        item("rice", 100, "grams"),
                        item("onion", 0.5, "items"),
                        item("garlic", 1, "cloves")),
                steps("Chop the onion and garlic.",
                        "Cook the chicken thoroughly.",
                        "Cook the rice according to its instructions.",
                        "Combine the chicken, vegetables and rice.")));

        recipes.add(new Recipe(5, "Mashed Potatoes",
                ingredients(
                        item("potato", 2, "items"),
                        item("milk", 50, "ml"),
                        item("butter", 15, "grams")),
                steps("Peel and cut the potatoes.",
                        "Boil until soft.",
                        "Drain and mash the potatoes.",
                        "Mix in the milk and butter.")));

        recipes.add(new Recipe(6, "Banana Oatmeal",
                ingredients(
                        item("oats", 50, "grams"),
                        item("milk", 200, "ml"),
                        item("banana", 1, "items")),
                steps("Place oats and milk in a saucepan.",
                        "Cook while stirring until soft.",
                        "Slice the banana.",
                        "Serve the oatmeal topped with banana.")));

        recipes.add(new Recipe(7, "Fruit Yogurt Bowl",
                ingredients(
                        item("yogurt", 150, "grams"),
                        item("banana", 1, "items"),
                        item("apple", 1, "items")),
                steps("Spoon the yogurt into a bowl.",
                        "Wash and chop the apple.",
                        "Slice the banana.",
                        "Arrange the fruit over the yogurt.")));

        recipes.add(new Recipe(8, "Vegetable Fried Rice",
                ingredients(
                        item("rice", 150, "grams"),
                        item("carrot", 1, "items"),
                        item("onion", 0.5, "items"),
                        item("oil", 15, "ml")),
                steps("Cook the rice.",
                        "Peel and chop the carrot and onion.",
                        "Heat the oil in a pan.",
                        "Cook the vegetables and stir in the rice.")));

        recipes.add(new Recipe(9, "Spinach Omelette",
                ingredients(
                        item("eggs", 2, "items"),
                        item("spinach", 50, "grams"),
                        item("cheese", 30, "grams")),
                steps("Beat the eggs in a bowl.",
                        "Wash and chop the spinach.",
                        "Cook the spinach in a pan.",
                        "Add the eggs and cheese and cook until firm.")));

        recipes.add(new Recipe(10, "Chicken Pasta",
                ingredients(
                        item("chicken", 150, "grams"),
                        item("pasta", 100, "grams"),
                        item("garlic", 1, "cloves"),
                        item("tomato", 1, "items")),
                steps("Boil the pasta.",
                        "Cut the chicken into small pieces.",
                        "Cook the chicken thoroughly with garlic.",
                        "Add chopped tomato and cook until softened.",
                        "Mix the sauce with the drained pasta.")));

        recipes.add(new Recipe(11, "Potato and Carrot Soup",
                ingredients(
                        item("potato", 2, "items"),
                        item("carrot", 1, "items"),
                        item("onion", 0.5, "items"),
                        item("milk", 200, "ml")),
                steps("Peel and chop the vegetables.",
                        "Boil them in water until soft.",
                        "Mash or blend the cooked vegetables.",
                        "Stir in the milk and heat gently.")));

        recipes.add(new Recipe(12, "Bean and Tomato Salad",
                ingredients(
                        item("beans", 150, "grams"),
                        item("tomato", 1, "items"),
                        item("onion", 0.5, "items")),
                steps("Drain and rinse the beans if canned.",
                        "Chop the tomato and onion.",
                        "Combine all ingredients in a bowl.",
                        "Mix gently and serve.")));

        recipes.add(new Recipe(13, "Pancakes",
                ingredients(
                        item("flour", 100, "grams"),
                        item("eggs", 1, "items"),
                        item("milk", 150, "ml"),
                        item("butter", 15, "grams")),
                steps("Mix the flour, eggs and milk into a batter.",
                        "Melt a little butter in a pan.",
                        "Pour in some batter.",
                        "Cook both sides until golden.")));

        recipes.add(new Recipe(14, "Cabbage and Carrot Stir-Fry",
                ingredients(
                        item("cabbage", 150, "grams"),
                        item("carrot", 1, "items"),
                        item("onion", 0.5, "items"),
                        item("oil", 15, "ml")),
                steps("Wash and slice the cabbage.",
                        "Peel and grate the carrot.",
                        "Chop the onion.",
                        "Stir-fry the vegetables in hot oil until tender.")));

        recipes.add(new Recipe(15, "Garlic Tomato Toast",
                ingredients(
                        item("bread", 2, "slices"),
                        item("tomato", 1, "items"),
                        item("garlic", 1, "cloves"),
                        item("butter", 10, "grams")),
                steps("Chop the tomato and garlic.",
                        "Mix them together.",
                        "Spread butter on the bread.",
                        "Add the tomato mixture and toast until ready.")));

        return recipes;
    }

    // Creates one ingredient requirement with its quantity and unit.
    private static RecipeIngredient item(
            String name, double quantity, String unit) {

        return new RecipeIngredient(name, quantity, unit);
    }

    // Groups ingredient requirements into a list for a recipe.
    private static ArrayList<RecipeIngredient> ingredients(
            RecipeIngredient... items) {

        ArrayList<RecipeIngredient> result = new ArrayList<>();

        for (RecipeIngredient ingredient : items) {
            result.add(ingredient);
        }

        return result;
    }

    // Combines preparation instructions into one numbered String.
    private static String steps(String... instructions) {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < instructions.length; i++) {
            result.append(i + 1)
                    .append(". ")
                    .append(instructions[i]);

            if (i < instructions.length - 1) {
                result.append("\n\n");
            }
        }

        return result.toString();
    }
}
