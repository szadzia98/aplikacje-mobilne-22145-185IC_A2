package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button number0,number1,number2,number3,number4,number5,number6,
            number7,number8,number9,clear,delete,divide,minus,plus,equal,
            times,procent,dot;
    TextView equation;
    EditText equalText;
    double firstNum = 0, secondNum = 0;
    int sign = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        number0 = (Button) findViewById(R.id.number0);
        number1 = (Button) findViewById(R.id.number1);
        number2 = (Button) findViewById(R.id.number2);
        number3 = (Button) findViewById(R.id.number3);
        number4 = (Button) findViewById(R.id.number4);
        number5 = (Button) findViewById(R.id.number5);
        number6 = (Button) findViewById(R.id.number6);
        number7 = (Button) findViewById(R.id.number7);
        number8 = (Button) findViewById(R.id.number8);
        number9 = (Button) findViewById(R.id.number9);
        clear = (Button) findViewById(R.id.Clear);
        delete = (Button) findViewById(R.id.delete);
        divide = (Button) findViewById(R.id.divide);
        minus = (Button) findViewById(R.id.minus);
        plus = (Button) findViewById(R.id.plus);
        times = (Button) findViewById(R.id.times);
        procent = (Button) findViewById(R.id.procent);
        dot = (Button) findViewById(R.id.dot);
        equal = (Button) findViewById(R.id.equal);
        equation = (TextView) findViewById(R.id.equation);
        equalText = (EditText) findViewById(R.id.equalEdit);

        number0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equalText.setText(equalText.getText() + "0");
            }
        });

        number1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equalText.setText(equalText.getText() + "1");
            }
        });

        number2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equalText.setText(equalText.getText() + "2");
            }
        });

        number3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equalText.setText(equalText.getText() + "3");
            }
        });

        number4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equalText.setText(equalText.getText() + "4");
            }
        });

        number5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equalText.setText(equalText.getText() + "5");
            }
        });

        number6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equalText.setText(equalText.getText() + "6");
            }
        });

        number7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equalText.setText(equalText.getText() + "7");
            }
        });

        number8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equalText.setText(equalText.getText() + "8");
            }
        });

        number9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equalText.setText(equalText.getText() + "9");
            }
        });

        number0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equalText.setText(equalText.getText() + "0");
            }
        });

        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equalText.setText(equalText.getText() + ".");

            }
        });

        clear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                equation.setText("");
                equalText.setText("");
                sign = 1;
                firstNum = 0;
                secondNum = 0;
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = equalText.getText().toString();
                char temp2[] = temp.toCharArray();
                char temp3[] = new char[ temp2.length - 1];
                for (int i = 0; i < temp2.length - 1; i++) {
                    temp3[i] = temp2[i];
                }
                StringBuilder temp4 = new StringBuilder("");
                temp4.append(temp3);
                equalText.setText(temp4);
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //2
                secondNum = Double.parseDouble(equalText.getText().toString());
                firstNum = Equation(firstNum,secondNum,sign);
                equation.setText(equation.getText() + Double.toString(secondNum) + "-");
                equalText.setText("");
                sign = 2;
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1
                secondNum = Double.parseDouble(equalText.getText().toString());
                firstNum = Equation(firstNum,secondNum,sign);
                equation.setText(equation.getText() + Double.toString(secondNum) + "+");
                equalText.setText("");
                sign = 1;
            }
        });

        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //3
                secondNum = Double.parseDouble(equalText.getText().toString());
                firstNum = Equation(firstNum,secondNum,sign);
                equation.setText(equation.getText() + Double.toString(secondNum) + "/");
                equalText.setText("");
                sign = 3;
            }
        });

        times.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //4
                secondNum = Double.parseDouble(equalText.getText().toString());
                firstNum = Equation(firstNum,secondNum,sign);
                equation.setText(equation.getText() + Double.toString(secondNum) + "*");
                equalText.setText("");
                sign = 4;
            }
        });

        procent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondNum = Double.parseDouble(equalText.getText().toString());
                secondNum = secondNum/100;
                equalText.setText(Double.toString(secondNum));
            }
        });

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondNum = Double.parseDouble(equalText.getText().toString());
                equation.setText(equation.getText() + Double.toString(secondNum) + "=");
                firstNum = Equation(firstNum,secondNum,sign);
                equalText.setText(Double.toString(firstNum));
                ;
            }
        });


        }
    public double Equation (double num1, double num2, int signEq) {
        // siqns: 1-plus,2-minus,3-divide,4-times
        switch (signEq) {
            case 1:
                num1 = num1 + num2;
                break;
            case 2:
                num1 = num1 - num2;
                break;
            case 3:
                num1 = num1 / num2;
                break;
            case 4:
                num1 = num1 * num2;
                break;
        }
        return num1;
    }


    }
