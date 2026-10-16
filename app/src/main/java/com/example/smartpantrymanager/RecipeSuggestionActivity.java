
package com.example.smartpantrymanager;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

// Displays recipes that can be prepared using pantry ingredients.
public class RecipeSuggestionActivity extends AppCompatActivity {

    // Key used to receive pantry ingredient names from another screen.
    public static final String EXTRA_PANTRY_INGREDIENTS =
            "pantry_ingredients";

    // Displays the available recipes.
    private ListView recipeListView;

    // Displays a message when no recipes can be prepared.
    private TextView emptyMessage;

    // Stores the recipes that match the pantry ingredients.
    private ArrayList<Recipe> matchingRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the title displayed for this screen.
        setTitle("Recipe Suggestions");

        // Create the main vertical layout programmatically.
        // This means we do not need an XML layout for this screen yet.
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(24, 24, 24, 24);

        // Create a heading for the screen.
        TextView heading = new TextView(this);
        heading.setText("Recipes You Can Make");
        heading.setTextSize(24);
        heading.setGravity(Gravity.CENTER);
        heading.setPadding(0, 0, 0, 20);

        // Create a message for when no recipes match.
        emptyMessage = new TextView(this);
        emptyMessage.setTextSize(17);
        emptyMessage.setGravity(Gravity.CENTER);
        emptyMessage.setText(
                "No matching recipes found.\n" +
                        "Add your pantry ingredients first."
        );

        // Create a list to display matching recipes.
        recipeListView = new ListView(this);

        // Add the components to the main layout.
        layout.addView(heading);
        layout.addView(emptyMessage);
        layout.addView(
                recipeListView,
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        0,
                        1
                )
        );

        // Display the completed layout on the screen.
        setContentView(layout);

        // Load recipes that match the pantry ingredients.
        loadMatchingRecipes();

        // Display recipe details when the user taps a recipe.
        recipeListView.setOnItemClickListener(
                (parent, view, position, id) -> {
                    Recipe selectedRecipe = matchingRecipes.get(position);

                    StringBuilder ingredientsText = new StringBuilder();
                    for (String ingredient :
                            selectedRecipe.getRequiredIngredients()) {
                        ingredientsText.append("• ")
                                .append(ingredient)
                                .append("\n");
                    }

                    Intent intent = new Intent(
                            RecipeSuggestionActivity.this,
                            RecipeDetailActivity.class
                    );
                    intent.putExtra(
                            RecipeDetailActivity.EXTRA_RECIPE_NAME,
                            selectedRecipe.getName()
                    );
                    intent.putExtra(
                            RecipeDetailActivity.EXTRA_RECIPE_INGREDIENTS,
                            ingredientsText.toString()
                    );
                    intent.putExtra(
                            RecipeDetailActivity.EXTRA_RECIPE_STEPS,
                            selectedRecipe.getPreparationSteps()
                    );

                    startActivity(intent);
                }
        );
    }

    // Finds recipes whose required ingredients are all available.
    private void loadMatchingRecipes() {

        // Retrieve pantry ingredient names sent by another activity.
        ArrayList<String> pantryNames =
                getIntent().getStringArrayListExtra(
                        EXTRA_PANTRY_INGREDIENTS
                );

        // If no ingredients were received, use an empty list.
        if (pantryNames == null) {
            pantryNames = new ArrayList<>();
        }

        // Convert the ingredient names into Ingredient objects
        // so they can be checked by the existing RecipeMatcher.
        ArrayList<Ingredient> pantryIngredients =
                new ArrayList<>();

        for (String ingredientName : pantryNames) {

            // Ignore empty ingredient names.
            if (ingredientName != null
                    && !ingredientName.trim().isEmpty()) {

                // Quantity is set to 1 temporarily because this
                // version checks ingredient presence, not quantities
                // required by individual recipes.
                pantryIngredients.add(
                        new Ingredient(
                                0,
                                ingredientName.trim(),
                                1.0,
                                "item",
                                ""
                        )
                );
            }
        }

        // Load all 15 preloaded recipes.
        ArrayList<Recipe> allRecipes =
                RecipeData.getAllRecipes();

        // Start with an empty list of matching recipes.
        matchingRecipes = new ArrayList<>();

        // Check each recipe against the pantry.
        for (Recipe recipe : allRecipes) {

            // Add a recipe only if every required ingredient exists.
            if (RecipeMatcher.canMakeRecipe(
                    recipe,
                    pantryIngredients)) {

                matchingRecipes.add(recipe);
            }
        }

        // Prepare the recipe names for display in the ListView.
        ArrayList<String> recipeNames = new ArrayList<>();

        for (Recipe recipe : matchingRecipes) {
            recipeNames.add(recipe.getName());
        }

        // Connect the recipe names to the ListView.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                recipeNames
        );

        recipeListView.setAdapter(adapter);

        // Show a helpful message when no recipes match.
        if (matchingRecipes.isEmpty()) {

            emptyMessage.setVisibility(View.VISIBLE);
            recipeListView.setVisibility(View.GONE);

        } else {

            // Hide the message when matching recipes exist.
            emptyMessage.setVisibility(View.GONE);
            recipeListView.setVisibility(View.VISIBLE);
        }
    }

    // Displays the ingredients and preparation instructions.
     }
}
