package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button scroll1, scroll2, highop, swip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scroll1 = (Button) findViewById(R.id.ScrollView1Button);
        scroll2 = (Button) findViewById(R.id.ScrollView2Button);
        highop = (Button) findViewById(R.id.HOButton);
        swip = (Button) findViewById(R.id.SwipeButton);

        scroll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, First.class);
                startActivity(intent);
            }
        });

        scroll2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Second.class);
                startActivity(intent);
            }
        });

        highop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Third.class);
                startActivity(intent);
            }
        });

        swip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Fourth.class);
                startActivity(intent);
            }
        });
    }
}