package com.example.cs212finalslabactivity2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This program calculates the change of a transaction
 * It displays a breakdown of the change
 *
 * @author Benny Gil A. Lactaotao
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editTextTotalPrice = findViewById(R.id.editTextTotalPrice);
        final EditText editTextCashOnHand = findViewById(R.id.editTextCashOnHand);
        final Button buttonCalculateChange = findViewById(R.id.buttonCalculateChange);
        final TextView balanceBreakdown = findViewById(R.id.balanceBreakdown);
        final TextView descriptionText = findViewById(R.id.descriptionText);
        String description =
                        "Program by:   Benny Gil A. Lactaotao" +
                        "\nSchedule:     WS 4:30 - 6:00 pm" +
                        "\nDate Created: November 22, 2023" +
                        "\n\nThis program calculates the change of a transaction.";
        descriptionText.setText(description);


        buttonCalculateChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the values from EditText fields
                double totalPrice = Double.parseDouble(editTextTotalPrice.getText().toString());
                double cashOnHand = Double.parseDouble(editTextCashOnHand.getText().toString());

                // Calculate the change and count of bills/coins
                double change = cashOnHand - totalPrice;

                // Display the result
                String result;
                if (change < 0)
                        result = "Balance is less than the transaction price";
                else result="Total Change: ₱" + change + "\n" + calculateBreakdown(change);

                // Add logic here to identify the count of bills/coins and append to the result
                balanceBreakdown.setText(result);
            }
        });
    }

    /**
     * Calculates the breakdown of a given amount
     *
     * @param amount amount to be broken down
     * @return String of breakdown
     */
    private static String calculateBreakdown(double amount) {
        // Array of bills and coins
        int[] billsAndCoins = {1000, 500, 200, 100, 50, 20, 10, 5, 1};
        int[] count = new int[billsAndCoins.length];

        for (int i = 0; i < billsAndCoins.length; i++) {
            // Calculate the count for each bill or coin
            count[i] = (int) (amount / billsAndCoins[i]);
            // Update the amount for the next iteration
            amount %= billsAndCoins[i];
        }
        // Create a string to display the breakdown
        StringBuilder breakdown = new StringBuilder("\nBreakdown:\n");

        // Append the breakdown for each bill or coin
        for (int i = 0; i < billsAndCoins.length; i++) {
            if (count[i] > 0) {
                breakdown.append("      ").append(count[i]).append("x  ₱").append(billsAndCoins[i]).append("\n");
            }
        }
        return breakdown.toString();
    }
}
