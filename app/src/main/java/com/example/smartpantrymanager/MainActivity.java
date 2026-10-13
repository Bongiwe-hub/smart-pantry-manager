
package com.example.smartpantrymanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Declare the buttons used on the home screen.
    private Button addIngredientButton;
    private Button suggestRecipesButton;
    private Button settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect this Java Activity to activity_main.xml.
        setContentView(R.layout.activity_main);

        // Find each button using its ID from the XML file.
        addIngredientButton = findViewById(R.id.addIngredientButton);
        suggestRecipesButton = findViewById(R.id.suggestRecipesButton);
        settingsButton = findViewById(R.id.settingsButton);

        // Respond when the user taps Add Ingredient.
        addIngredientButton.setOnClickListener(view -> {
            Toast.makeText(
                    MainActivity.this,
                    "Add Ingredient selected",
                    Toast.LENGTH_SHORT
            ).show();
        });

        // Respond when the user taps Suggested Recipes.
        suggestRecipesButton.setOnClickListener(view -> {
            Toast.makeText(
                    MainActivity.this,
                    "Suggested Recipes selected",
                    Toast.LENGTH_SHORT
            ).show();
        });

        // Respond when the user taps Settings.
        settingsButton.setOnClickListener(view -> {
            Toast.makeText(
                    MainActivity.this,
                    "Settings selected",
                    Toast.LENGTH_SHORT
            ).show();
        });
    }
}
