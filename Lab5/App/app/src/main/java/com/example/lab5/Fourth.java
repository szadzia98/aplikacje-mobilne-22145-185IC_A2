package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class Fourth extends AppCompatActivity {

    private SlidrInterface slidr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        slidr = Slidr.attach(this);
    }

    public void lockSlide(View v) {
        slidr.lock();
    }
    public void unlockSlide(View v) {
        slidr.unlock();
    }
}