package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import net.objecthunter.exp4j.ExpressionBuilder;
import java.text.DecimalFormat;
public class MainActivity extends AppCompatActivity {

    private TextView inputText;
    private TextView outputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.input);
        outputText = findViewById(R.id.output);

        inputText.setText("");
        outputText.setText("");

        setButtonListeners();
    }

    private void setButtonListeners() {
        findViewById(R.id.button_bracket_left).setOnClickListener(v -> addToInputText("("));
        findViewById(R.id.button_bracket_right).setOnClickListener(v -> addToInputText(")"));
        findViewById(R.id.button_0).setOnClickListener(v -> addToInputText("0"));
        findViewById(R.id.button_1).setOnClickListener(v -> addToInputText("1"));
        findViewById(R.id.button_2).setOnClickListener(v -> addToInputText("2"));
        findViewById(R.id.button_3).setOnClickListener(v -> addToInputText("3"));
        findViewById(R.id.button_4).setOnClickListener(v -> addToInputText("4"));
        findViewById(R.id.button_5).setOnClickListener(v -> addToInputText("5"));
        findViewById(R.id.button_6).setOnClickListener(v -> addToInputText("6"));
        findViewById(R.id.button_7).setOnClickListener(v -> addToInputText("7"));
        findViewById(R.id.button_8).setOnClickListener(v -> addToInputText("8"));
        findViewById(R.id.button_9).setOnClickListener(v -> addToInputText("9"));
        findViewById(R.id.button_dot).setOnClickListener(v -> addToInputText("."));
        findViewById(R.id.button_division).setOnClickListener(v -> addToInputText("÷"));
        findViewById(R.id.button_multiply).setOnClickListener(v -> addToInputText("×"));
        findViewById(R.id.button_subtraction).setOnClickListener(v -> addToInputText("—"));
        findViewById(R.id.button_addition).setOnClickListener(v -> addToInputText("+"));
        findViewById(R.id.button_equals).setOnClickListener(v -> showResult());
        findViewById(R.id.button_percent).setOnClickListener(v -> addToInputText("%"));
        findViewById(R.id.button_clear).setOnClickListener(v -> clearInputOutput());
    }

    // добавление текста к полю ввода
    private void addToInputText(String value) {
        inputText.append(value);
    }


    private void clearInputOutput() {
        inputText.setText("");
        outputText.setText("");
    }
    private String getInputExpression() {
        return inputText.getText().toString();
    }

    //вывод результата
    private void showResult() {
        try {

            String expression = getInputExpression();
            expression = expression.replace("÷", "/")
                    .replace("×", "*")
                    .replace("—", "-")
                    .replace("%", "/100");


            double result = new ExpressionBuilder(expression).build().evaluate();


            String formattedResult = new DecimalFormat("0.######").format(result);

            //  результат
            outputText.setText(formattedResult);
            outputText.setTextColor(ContextCompat.getColor(this, R.color.neon_green));

        } catch (Exception e) {
            // при ошибке
            outputText.setText("Ошибка");
            outputText.setTextColor(ContextCompat.getColor(this, R.color.red));
        }
    }
}