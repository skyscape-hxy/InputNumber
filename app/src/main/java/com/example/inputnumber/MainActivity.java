package com.example.inputnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InputNumberView inputView = findViewById(R.id.inputView);
        inputView.setMax(10);
        inputView.setMin(-10);
    }
}