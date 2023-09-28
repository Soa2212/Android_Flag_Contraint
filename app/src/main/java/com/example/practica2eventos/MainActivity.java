package com.example.practica2eventos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Stack;

//No pude hacer las restas de negativos

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private void agregarTexto(String texto) {
        String currentText = textView.getText().toString();
        if (currentText.equals("0")) {
            currentText = texto;
        } else {
            currentText += texto;
        }
        textView.setText(currentText);
    }
    private String evaluateExpression(String expression) {
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char currentChar = expression.charAt(i);

            if (Character.isDigit(currentChar) || currentChar == '.') {
                StringBuilder numberBuilder = new StringBuilder();
                numberBuilder.append(currentChar);

                while (i + 1 < expression.length() &&
                        (Character.isDigit(expression.charAt(i + 1)) || expression.charAt(i + 1) == '.')) {
                    numberBuilder.append(expression.charAt(i + 1));
                    i++;
                }

                double number = Double.parseDouble(numberBuilder.toString());
                numbers.push(number);
            } else if (currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/') {
                while (!operators.isEmpty() && hasPrecedence(currentChar, operators.peek())) {
                    applyOperator(numbers, operators);
                }
                operators.push(currentChar);
            }
        }

        while (!operators.isEmpty()) {
            applyOperator(numbers, operators);
        }

        if (numbers.isEmpty()) {
            return "Error: Expresión inválida";
        }

        double result = numbers.pop();
        String formattedResult = String.format("%.2f", result);
        formattedResult = formattedResult.replaceAll("\\.?0*$", "");
        return formattedResult;
    }
    private boolean hasPrecedence(char op1, char op2) {
        return (op2 == '*' || op2 == '/') && (op1 == '+' || op1 == '-');
    }
    private void applyOperator(Stack<Double> numbers, Stack<Character> operators) {
        char operator = operators.pop();
        double num2 = numbers.pop();
        double num1 = numbers.pop();

        double result = 0;
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 != 0) {
                    result = num1 / num2;
                }
                break;
        }

        numbers.push(result);
    }
    private void appendToExpression(String operator) {
        String currentText = textView.getText().toString();

        if (!currentText.isEmpty()) {
            char lastChar = currentText.charAt(currentText.length() - 1);

            if (lastChar == '+' || lastChar == '-' || lastChar == '*' || lastChar == '/' || lastChar == '.') {
                currentText = currentText.substring(0, currentText.length() - 1);
            }

            currentText += operator;
            textView.setText(currentText);
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.TextV);
        Button button1 = findViewById(R.id.N1);
        Button button2 = findViewById(R.id.N2);
        Button button3 = findViewById(R.id.N3);
        Button button4 = findViewById(R.id.N4);
        Button button5 = findViewById(R.id.N5);
        Button button6 = findViewById(R.id.N6);
        Button button7 = findViewById(R.id.N7);
        Button button8 = findViewById(R.id.N8);
        Button button9 = findViewById(R.id.N9);
        Button button0 = findViewById(R.id.N0);
        Button buttonAC = findViewById(R.id.AC);
        Button buttonPlus = findViewById(R.id.Plus);
        Button buttonEquals = findViewById(R.id.Equals);
        Button buttonDot = findViewById(R.id.Dot);
        Button buttonDelete = findViewById(R.id.Delete);
        Button buttonMinus = findViewById(R.id.Minus);
        Button buttonMult = findViewById(R.id.Mult);
        Button buttonDiv = findViewById(R.id.Div);


        buttonDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendToExpression("/");
            }
        });


        buttonMult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendToExpression("*");
            }
        });

        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendToExpression("-");
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentText = textView.getText().toString();
                    if (currentText.length() == 1) {
                        textView.setText("0");
                    } else {
                        String newText = currentText.substring(0, currentText.length() - 1);
                        textView.setText(newText);
                    }
            }
        });



        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendToExpression(".");
            }
        });
 
        buttonEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentText = textView.getText().toString();
                    String result = evaluateExpression(currentText);
                    textView.setText(String.valueOf(result));

            }
        });

        buttonAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentText = textView.getText().toString();
                currentText = "0";
                textView.setText(currentText);
            }
        });

        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appendToExpression("+");
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarTexto("1");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarTexto("2");

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarTexto("3");

            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarTexto("4");

            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarTexto("5");

            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarTexto("6");

            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarTexto("7");

            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarTexto("8");

            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarTexto("9");

            }
        });
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarTexto("0");

            }
        });
    }
}