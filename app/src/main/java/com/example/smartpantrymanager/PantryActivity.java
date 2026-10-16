package com.example.smartpantrymanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PantryActivity extends AppCompatActivity {

    private ListView pantryListView;
    private TextView emptyPantryText;
    private ArrayList<Ingredient> ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ingredients = new ArrayList<>();

        ActivityResultLauncher<Intent> addIngredientLauncher =
                registerForActivityResult(
                        new ActivityResultContracts.StartActivityForResult(),
                        result -> {
                            if (result.getResultCode() == RESULT_OK
                                    && result.getData() != null) {

                                Intent data = result.getData();

                                String name =
                                        data.getStringExtra("ingredient_name");
                                double quantity =
                                        data.getDoubleExtra(
                                                "ingredient_quantity", 0
                                        );
                                String unit =
                                        data.getStringExtra("ingredient_unit");
                                String expiry =
                                        data.getStringExtra("ingredient_expiry");

                                ingredients.add(new Ingredient(
                                        ingredients.size() + 1,
                                        name,
                                        quantity,
                                        unit,
                                        expiry
                                ));

                                displayIngredients();
                            }
                        }
                );

        setContentView(R.layout.activity_pantry);

        pantryListView = findViewById(R.id.pantryListView);
        emptyPantryText = findViewById(R.id.emptyPantryText);

        Button addIngredientButton =
                findViewById(R.id.addIngredientButton);

        addIngredientButton.setOnClickListener(view -> {
            Intent intent = new Intent(
                    PantryActivity.this,
                    AddIngredientActivity.class
            );
            addIngredientLauncher.launch(intent);
        });

        Button suggestRecipesButton =
                findViewById(R.id.suggestRecipesButton);

        suggestRecipesButton.setOnClickListener(view -> {
            ArrayList<String> pantryNames = new ArrayList<>();

            for (Ingredient ingredient : ingredients) {
                pantryNames.add(ingredient.getName());
            }

            Intent intent = new Intent(
                    PantryActivity.this,
                    RecipeSuggestionActivity.class
            );
            intent.putStringArrayListExtra(
                    RecipeSuggestionActivity.EXTRA_PANTRY_INGREDIENTS,
                    pantryNames
            );

            startActivity(intent);
        });

        displayIngredients();
    }

    private void displayIngredients() {
        if (ingredients.isEmpty()) {
            emptyPantryText.setText(
                    "Your pantry is empty. Add an ingredient to get started."
            );
            emptyPantryText.setVisibility(View.VISIBLE);
            pantryListView.setVisibility(View.GONE);
            return;
        }

        ArrayList<String> ingredientNames = new ArrayList<>();

        for (Ingredient ingredient : ingredients) {
            ingredientNames.add(ingredient.toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                ingredientNames
        );

        pantryListView.setAdapter(adapter);
        pantryListView.setVisibility(View.VISIBLE);
        emptyPantryText.setVisibility(View.GONE);
    }
}