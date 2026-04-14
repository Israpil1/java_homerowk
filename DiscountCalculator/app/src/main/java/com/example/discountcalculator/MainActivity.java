package com.example.discountcalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText etPurchaseAmount = findViewById(R.id.etPurchaseAmount);
        Button btnCalculate = findViewById(R.id.btnCalculate);
        TextView tvResult = findViewById(R.id.tvResult);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = etPurchaseAmount.getText().toString();

                if (!input.isEmpty()) {
                    try {
                        double amount = Double.parseDouble(input);
                        double discountPercent = 0;

                        if (amount > 1000) {
                            discountPercent = 5;
                        } else if (amount > 500) {
                            discountPercent = 3;
                        }

                        double discountSum = amount * discountPercent / 100;
                        double finalPrice = amount - discountSum;

                        String result = String.format(Locale.US,
                                "Скидка: %.0f%%\nСумма скидки: %.2f руб.\nИтого: %.2f руб.",
                                discountPercent, discountSum, finalPrice);

                        tvResult.setText(result);

                    } catch (NumberFormatException e) {
                        tvResult.setText("Ошибка ввода числа");
                    }
                } else {
                    tvResult.setText("Введите сумму покупки!");
                }
            }
        });
    }
}