package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ThirdActivity extends AppCompatActivity {
    Button secondly3, firstly3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        secondly3 = (Button) findViewById(R.id.secondly3);
        firstly3 = (Button) findViewById(R.id.firstly3);

        secondly3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ThirdActivity.this, SecondActivity.class);
                startActivity(intent1);
            }
        });

        firstly3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(ThirdActivity.this, MainActivity.class);
                startActivity(intent2);
            }
        });
    }
}