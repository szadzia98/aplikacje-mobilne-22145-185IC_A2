package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class FirstActivity extends AppCompatActivity {

    Button mainPage, sort, unsort, filter;
    ListView list;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        mainPage = (Button) findViewById(R.id.mainListButton);
        sort = (Button) findViewById(R.id.sortButton);
        unsort = (Button) findViewById(R.id.unsortButton);
        filter = (Button) findViewById(R.id.filterButton);
        list = (ListView) findViewById(R.id.listViewList);

        ArrayList numbers = randomGenerator();

        initAdapter(numbers);

        mainPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList sortedNumbers = new ArrayList(numbers);
                Collections.sort(sortedNumbers);
                initAdapter(sortedNumbers);
            }
        });

        unsort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initAdapter(numbers);
            }
        });

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList filteredNumbers = new ArrayList();
                int temp;
                for(int i = 0; i<100;i++){
                    ArrayList sortedNumbers = new ArrayList(numbers);
                    Collections.sort(sortedNumbers);
                    temp = (int) sortedNumbers.get(i);
                    if (temp >= 0 && temp <= 100){
                        filteredNumbers.add(temp);
                    }
                }
                initAdapter(filteredNumbers);
            }
        });
    }
    ArrayList randomGenerator (){
        Random generator = new Random();
        ArrayList listNum = new ArrayList();
        for(int i=0;i<100;i++)
        {
            listNum.add(generator.nextInt(1000));
        }

        return  listNum;
    }
    private void initAdapter(ArrayList rowsArrayList) {

        adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item,rowsArrayList);
        list.setAdapter(adapter);
    }
}