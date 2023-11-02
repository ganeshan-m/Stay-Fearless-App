package com.example.fearless;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Add_contacts extends AppCompatActivity {

    private EditText enterNameEditText;
    private EditText enterNoEditText;
    private TextView validationErrorTextView;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacts);

        enterNameEditText = findViewById(R.id.enter_name);
        enterNoEditText = findViewById(R.id.enter_no);
        validationErrorTextView = findViewById(R.id.validation_error);
        saveButton = findViewById(R.id.save_button);

        enterNameEditText.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        enterNoEditText.setInputType(InputType.TYPE_CLASS_NUMBER);

        enterNoEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // No action needed
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // No action needed
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Hide the validation error message when the user starts typing in the insert number field
                validationErrorTextView.setVisibility(View.GONE);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveContact();
            }
        });
    }
    private void saveContact() {
        String name = enterNameEditText.getText().toString().trim();
        String number = enterNoEditText.getText().toString().trim();

        if (!isNotEmpty(name)) {
            showValidationError("Please enter a name.");
            return;
        }

        if (!isAlphabetical(name)) {
            showValidationError("Please enter a valid name with only alphabetic characters.");
            return;
        }

        if (!isNotEmpty(number)) {
            showValidationError("Please enter a number.");
            return;
        }

        if (!isNumeric(number)) {
            showValidationError("Please enter a valid number with only numeric characters.");
            return;
        }

        // Save the contact to Shared Preferences
        updateSharedPreferences(name, number);

        // Display a success message
        String successMessage = "Contact saved successfully";
        Toast.makeText(this, successMessage, Toast.LENGTH_SHORT).show();

        // Clear the input fields
        enterNameEditText.getText().clear();
        enterNoEditText.getText().clear();
    }

    private void updateSharedPreferences(String name, String number) {
        SharedPreferences sharedPreferences = getSharedPreferences("EmergencyContacts", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(name, number);
        editor.apply();
    }

    private void showValidationError(String errorMessage) {
        validationErrorTextView.setText(errorMessage);
        validationErrorTextView.setVisibility(View.VISIBLE);
        validationErrorTextView.setTextColor(getResources().getColor(R.color.colorError));
    }

    private boolean isAlphabetical(String text) {
        return text.matches("^[a-zA-Z ]+$");
    }

    private boolean isNumeric(String text) {
        return text.matches("^[0-9]+$");
    }

    private boolean isNotEmpty(String text) {
        return !TextUtils.isEmpty(text);
    }
}
