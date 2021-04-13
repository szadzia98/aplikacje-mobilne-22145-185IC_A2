package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    Button firstly2, thirdly2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        firstly2 = (Button) findViewById(R.id.firstly2);
        thirdly2 = (Button) findViewById(R.id.thirdly2);

        firstly2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        });

        thirdly2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent2);
            }
        });
    }
}