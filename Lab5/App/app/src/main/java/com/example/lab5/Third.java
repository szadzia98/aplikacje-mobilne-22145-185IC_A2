package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Third extends AppCompatActivity {

    Button main, highlight, opacity;
    Drawable bacckground1 = highlight.getBackground();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        main = (Button) findViewById(R.id.main3);
        highlight = (Button) findViewById(R.id.highlightButton);
        opacity = (Button) findViewById(R.id.opacityButton);

        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Third.this, MainActivity.class);
                startActivity(intent);
            }
        });

        highlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(highlight.getBackground() == bacckground1 )
                {
                    highlight.setBackground(getResources().getDrawable(R.drawable.highlight2));
                }else{
                    highlight.setBackground(bacckground1);
                }
            }
        });

        opacity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (opacity.getAlpha() == 1){
                    opacity.setAlpha((float)0.5);
                }else
                {
                    opacity.setAlpha((float)1);
                }

            }
        });
    }
}