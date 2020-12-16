package com.example.androidsqliteexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textViewEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewEmail = findViewById(R.id.textViewEmail);

        String nameFromIntent = getIntent().getStringExtra("EMAIL");
        textViewEmail.setText("WELCOME " + nameFromIntent);
    }
}
