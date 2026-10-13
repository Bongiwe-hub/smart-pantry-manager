
package com.example.smartpantrymanager;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

// Controls the screen that displays pantry ingredients.
public class PantryActivity extends AppCompatActivity {

    // Displays the ingredients in a scrollable list.
    private ListView pantryListView;

    // Displays a message when the pantry has no ingredients.
    private TextView emptyPantryText;

    // Temporarily stores the ingredient objects in memory.
    private ArrayList<Ingredient> ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the XML layout for the pantry screen.
        setContentView(R.layout.activity_pantry);

        // Connect the Java variables to the XML components.
        pantryListView = findViewById(R.id.pantryListView);
        emptyPantryText = findViewById(R.id.emptyPantryText);

        // Create an empty list until we connect the other screens.
        ingredients = new ArrayList<>();

        // Display the current pantry contents.
        displayIngredients();
    }

    // Refreshes the visible pantry list.
    private void displayIngredients() {

        // Check whether there are any ingredients to display.
        if (ingredients.isEmpty()) {

            // Tell the user how to get started.
            emptyPantryText.setText(
                    "Your pantry is empty. Add an ingredient to get started."
            );

            // Show the empty message.
            emptyPantryText.setVisibility(View.VISIBLE);

            // Hide the list because there is nothing to display.
            pantryListView.setVisibility(View.GONE);

            return;
        }

        // Create a list of readable ingredient descriptions.
        ArrayList<String> ingredientNames = new ArrayList<>();

        // Convert each Ingredient object into text.
        for (Ingredient ingredient : ingredients) {
            ingredientNames.add(ingredient.toString());
        }

        // Create an adapter that supplies text rows to the ListView.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                ingredientNames
        );

        // Attach the adapter to the ListView.
        pantryListView.setAdapter(adapter);

        // Show the list and hide the empty message.
        pantryListView.setVisibility(View.VISIBLE);
        emptyPantryText.setVisibility(View.GONE);
    }
}
