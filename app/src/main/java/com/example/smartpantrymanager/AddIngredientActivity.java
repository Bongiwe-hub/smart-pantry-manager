
package com.example.smartpantrymanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

// This activity allows the user to enter a new pantry ingredient.
public class AddIngredientActivity extends AppCompatActivity {

    // Input fields from activity_add_ingredient.xml.
    private EditText nameInput;
    private EditText quantityInput;
    private EditText unitInput;
    private EditText expiryInput;

    // Button that submits the ingredient form.
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the ingredient form.
        setContentView(R.layout.activity_add_ingredient);

        // Connect the Java variables to their XML views.
        nameInput = findViewById(R.id.ingredientNameInput);
        quantityInput = findViewById(R.id.ingredientQuantityInput);
        unitInput = findViewById(R.id.ingredientUnitInput);
        expiryInput = findViewById(R.id.ingredientExpiryInput);
        saveButton = findViewById(R.id.saveIngredientButton);

        // Run saveIngredient() when the user taps Save.
        saveButton.setOnClickListener(view -> saveIngredient());
    }

    // Validates the form and sends the ingredient back to PantryActivity.
    private void saveIngredient() {

        // Read the entered values and remove extra spaces.
        String name = nameInput.getText().toString().trim();
        String quantityText =
                quantityInput.getText().toString().trim();
        String unitText = unitInput.getText().toString().trim();
        String expiry = expiryInput.getText().toString().trim();

        // The ingredient name is required.
        if (name.isEmpty()) {
            nameInput.setError("Enter an ingredient name");
            nameInput.requestFocus();
            return;
        }

        // The quantity is required.
        if (quantityText.isEmpty()) {
            quantityInput.setError("Enter a quantity");
            quantityInput.requestFocus();
            return;
        }

        // The unit is required.
        if (unitText.isEmpty()) {
            unitInput.setError("Enter a unit, e.g. grams or items");
            unitInput.requestFocus();
            return;
        }

        // Convert the quantity from text to a number.
        double quantity;

        try {
            quantity = Double.parseDouble(quantityText);
        } catch (NumberFormatException exception) {
            quantityInput.setError("Enter a valid number, e.g. 2 or 1.5");
            quantityInput.requestFocus();
            return;
        }

        // Reject quantities that are zero, negative or not finite.
        if (!Double.isFinite(quantity) || quantity <= 0) {
            quantityInput.setError("Quantity must be greater than zero");
            quantityInput.requestFocus();
            return;
        }

        // Standardise the unit for consistent recipe matching.
        String unit = standardiseUnit(unitText);

        if (unit == null) {
            unitInput.setError(
                    "Use grams, kg, ml, litres, items, slices or cloves"
            );
            unitInput.requestFocus();
            return;
        }

        // Package the validated ingredient details.
        Intent result = new Intent();
        result.putExtra("ingredient_name", name);
        result.putExtra("ingredient_quantity", quantity);
        result.putExtra("ingredient_unit", unit);
        result.putExtra("ingredient_expiry", expiry);

        // Return the details to PantryActivity.
        setResult(RESULT_OK, result);

        Toast.makeText(
                AddIngredientActivity.this,
                "Ingredient details saved",
                Toast.LENGTH_SHORT
        ).show();

        // Close this screen and return to the pantry.
        finish();
    }

    // Converts common unit spellings into standard names.
    private String standardiseUnit(String unitText) {

        String unit = unitText.trim().toLowerCase(Locale.ROOT);

        switch (unit) {
            case "g":
            case "gram":
            case "grams":
                return "grams";

            case "kg":
            case "kilogram":
            case "kilograms":
                return "kg";

            case "ml":
            case "millilitre":
            case "millilitres":
            case "milliliter":
            case "milliliters":
                return "ml";

            case "l":
            case "litre":
            case "litres":
            case "liter":
            case "liters":
                return "litres";

            case "item":
            case "items":
            case "piece":
            case "pieces":
                return "items";

            case "slice":
            case "slices":
                return "slices";

            case "clove":
            case "cloves":
                return "cloves";

            default:
                return null;
        }
    }
}
