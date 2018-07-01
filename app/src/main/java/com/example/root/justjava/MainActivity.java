package com.example.root.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity {

    int quantity = 1;
    int price_of_coffee = 6;
    boolean hasWhippedCream;
    boolean hasChocolate;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        hasChocolate = chocolateCheckBox.isChecked();

        EditText nameEditText = (EditText) findViewById(R.id.name_edit_text);
        String Name = nameEditText.getText().toString().trim();

        display(quantity);
        displaySummary(orderSummary(calculatePrice(quantity, basePrice(price_of_coffee, hasWhippedCream, hasChocolate)), Name, hasWhippedCream, hasChocolate));

    }

    private int basePrice(int price_of_coffee, boolean addWhippedCream, boolean addChocolateCream) {
        if (addChocolateCream == true)
            price_of_coffee += 2;
        if (addWhippedCream == true)
            price_of_coffee += 1;

        return price_of_coffee;
    }

    public void increment(View view) {
        quantity++;
        display(quantity);
    }

    public void decrement(View view) {
        if (quantity != 0)
            quantity--;
        display(quantity);
    }

    //Displays the quantity selected by quantity picker.
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /*Calculating the total price
     * quantity is the no of cups
     * price_of_coffee is the coffee price per cup.
     * */
    private int calculatePrice(int quantity, int price_of_coffee) {
        int price = quantity * price_of_coffee;
        return price;
    }

    /*
     * Displaying the summary in the textview.
     * */
    private void displaySummary(String sum) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(sum);
    }

    // A little bit of change
    /*
     * Creating summary of the order
     * Name = name of the customer
     * price = total price calculated rom calculatePrice() method
     * @return returning the whole summary as string
     *
     * */
    private String orderSummary(int price, String Name, boolean addWhippedCream, boolean addChocolate) {
        String sum = "Name: " + Name + "\nQuantity: " + quantity + "\nWhipped cream:\t " + addWhippedCream + "\nChocolate:\t " + addChocolate + "\nTotal: $" + price + "\nThank you!";
        return sum;
    }

}


