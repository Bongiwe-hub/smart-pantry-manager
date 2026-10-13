
package com.example.smartpantrymanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

// Controls the screen where users enter a new ingredient.
public class AddIngredientActivity extends AppCompatActivity {

    private EditText nameInput;
    private EditText quantityInput;
    private EditText unitInput;
    private EditText expiryInput;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the form's XML layout.
        setContentView(R.layout.activity_add_ingredient);

        // Connect Java variables to the XML input fields.
        nameInput = findViewById(R.id.ingredientNameInput);
        quantityInput = findViewById(R.id.ingredientQuantityInput);
        unitInput = findViewById(R.id.ingredientUnitInput);
        expiryInput = findViewById(R.id.ingredientExpiryInput);
        saveButton = findViewById(R.id.saveIngredientButton);

        // Run saveIngredient() when the user presses Save.
        saveButton.setOnClickListener(view -> saveIngredient());
    }

    private void saveIngredient() {

        // Read the values entered by the user.
        String name = nameInput.getText().toString().trim();
        String quantityText = quantityInput.getText().toString().trim();
        String unit = unitInput.getText().toString().trim();
        String expiry = expiryInput.getText().toString().trim();

        // Validate the ingredient name.
        if (name.isEmpty()) {
            nameInput.setError("Enter an ingredient name");
            nameInput.requestFocus();
            return;
        }

        // Validate the quantity field.
        if (quantityText.isEmpty()) {
            quantityInput.setError("Enter a quantity");
            quantityInput.requestFocus();
            return;
        }

        // Validate the measurement unit.
        if (unit.isEmpty()) {
            unitInput.setError("Enter a unit, e.g. grams");
            unitInput.requestFocus();
            return;
        }

        // Convert the quantity from text into a number.
        double quantity;

        try {
            quantity = Double.parseDouble(quantityText);
        } catch (NumberFormatException exception) {
            quantityInput.setError("Enter a valid number");
            quantityInput.requestFocus();
            return;
        }

        // Do not accept zero or negative quantities.
        if (quantity <= 0) {
            quantityInput.setError("Quantity must be greater than zero");
            quantityInput.requestFocus();
            return;
        }

        // Package the ingredient information for the previous screen.
        Intent result = new Intent();
        result.putExtra("ingredient_name", name);
        result.putExtra("ingredient_quantity", quantity);
        result.putExtra("ingredient_unit", unit);
        result.putExtra("ingredient_expiry", expiry);

        // Return the entered information to the calling Activity.
        setResult(RESULT_OK, result);

        Toast.makeText(
                this,
                "Ingredient details captured",
                Toast.LENGTH_SHORT
        ).show();

        // Close this screen and return to the previous screen.
        finish();
    }
}
