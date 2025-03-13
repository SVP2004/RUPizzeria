package com.example.rupizzeriaapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity to manage store orders, including viewing, canceling, and exporting orders.
 */
public class StoreOrdersActivity extends AppCompatActivity {

    private Spinner orderNumberSpinner;
    private EditText orderTotalDisplay;
    private ListView orderDetailsList;
    private Button cancelOrderButton;

    private List<Order> storeOrdersList;
    private ArrayAdapter<Order> orderAdapter;
    private ArrayAdapter<String> orderDetailsAdapter;

    /**
     * Called when the activity is created.
     * Initializes the UI components and configures interaction logic for managing store orders.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously
     *                           being shut down, this Bundle contains the data it most recently supplied.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);

        orderNumberSpinner = findViewById(R.id.order_number_spinner);
        orderTotalDisplay = findViewById(R.id.order_total_display);
        orderDetailsList = findViewById(R.id.order_details_list);
        cancelOrderButton = findViewById(R.id.cancel_order_button);

        setupBackButton();

        storeOrdersList = new ArrayList<>(Order.getAllOrders());
        setupOrderNumberSpinner();
        setupCancelOrderButton();

        cancelOrderButton.setEnabled(false);
    }

    /**
     * Configures the back button to return to the main activity.
     */
    private void setupBackButton() {
        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    /**
     * Sets up the spinner to display order numbers.
     */
    private void setupOrderNumberSpinner() {
        orderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, storeOrdersList);
        orderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderNumberSpinner.setAdapter(orderAdapter);

        orderNumberSpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                Order selectedOrder = storeOrdersList.get(position);
                displaySelectedOrder(selectedOrder);
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {
                clearOrderDetails();
            }
        });
    }

    /**
     * Displays the selected order details and total in the UI.
     *
     * @param selectedOrder The selected order to display.
     */
    private void displaySelectedOrder(Order selectedOrder) {
        if (selectedOrder != null) {
            List<String> pizzaDetails = new ArrayList<>();
            for (Pizza pizza : selectedOrder.getPizzas()) {
                pizzaDetails.add(pizza.toString());
            }
            orderDetailsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pizzaDetails);
            orderDetailsList.setAdapter(orderDetailsAdapter);

            double totalWithTax = selectedOrder.getTotal();
            orderTotalDisplay.setText(String.format("$%.2f", totalWithTax));

            cancelOrderButton.setEnabled(true);
        } else {
            clearOrderDetails();
        }
    }

    /**
     * Clears the order details and total display.
     */
    private void clearOrderDetails() {
        if (orderDetailsAdapter != null) {
            orderDetailsAdapter.clear();
        }
        orderTotalDisplay.setText("");
        cancelOrderButton.setEnabled(false);
    }

    /**
     * Sets up the "Cancel Order" button functionality with a confirmation dialog.
     */
    private void setupCancelOrderButton() {
        cancelOrderButton.setOnClickListener(v -> {
            int selectedPosition = orderNumberSpinner.getSelectedItemPosition();
            if (selectedPosition >= 0) {
                new AlertDialog.Builder(this)
                        .setTitle("Cancel Order")
                        .setMessage("Are you sure you want to cancel this order?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            Order selectedOrder = storeOrdersList.get(selectedPosition);

                            Order.getAllOrders().remove(selectedOrder);
                            storeOrdersList.remove(selectedOrder);
                            orderAdapter.notifyDataSetChanged();
                            clearOrderDetails();

                            Toast.makeText(this, "Order canceled successfully.", Toast.LENGTH_SHORT).show();
                        })
                        .setNegativeButton("No", null)
                        .show();
            } else {
                Toast.makeText(this, "No order selected.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}