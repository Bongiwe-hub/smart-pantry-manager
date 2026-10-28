package com.example.smartpantrymanager;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RecipeDetailActivity extends AppCompatActivity {

    public static final String EXTRA_RECIPE_NAME = "extra_recipe_name";
    public static final String EXTRA_RECIPE_INGREDIENTS =
            "extra_recipe_ingredients";
    public static final String EXTRA_RECIPE_STEPS = "extra_recipe_steps";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recipe_detail);

        TextView recipeTitle = findViewById(R.id.recipeTitle);
        TextView recipeIngredients = findViewById(R.id.recipeIngredients);
        TextView recipeSteps = findViewById(R.id.recipeSteps);

        String name = getIntent().getStringExtra(EXTRA_RECIPE_NAME);
        String ingredients =
                getIntent().getStringExtra(EXTRA_RECIPE_INGREDIENTS);
        String steps = getIntent().getStringExtra(EXTRA_RECIPE_STEPS);

        recipeTitle.setText(name == null ? "Recipe Details" : name);
        recipeIngredients.setText(ingredients == null ? "" : ingredients);
        recipeSteps.setText(steps == null ? "" : steps);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                (view, insets) -> {
                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars()
                    );
                    view.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );
                    return insets;
                }
        );
    }
}