package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateAccountActivity extends AppCompatActivity {

    private MyOpener dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        dbHelper = new MyOpener(this);

        EditText newUsername = findViewById(R.id.new_username);
        EditText newPassword = findViewById(R.id.new_password);
        EditText newEmail = findViewById(R.id.new_email);
        EditText newAge = findViewById(R.id.new_age);
        Button createAccountButton = findViewById(R.id.create_account_button);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = newUsername.getText().toString();
                String password = newPassword.getText().toString();
                String email = newEmail.getText().toString();
                int age = Integer.parseInt(newAge.getText().toString());

                if (username.isEmpty() || password.isEmpty() || email.isEmpty() || newAge.getText().toString().isEmpty()) {
                    Toast.makeText(CreateAccountActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                } else {
                    long result = dbHelper.addUser(username, email, password, age);

                    if (result != -1) {
                        Toast.makeText(CreateAccountActivity.this, "Compte créé avec succès", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(CreateAccountActivity.this, "Erreur lors de la création du compte", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
