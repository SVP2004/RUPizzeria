package com.example.rupizzeriaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.stream.Collectors;

/**
 * This class represents the activity for managing the user's current pizza order in the RU Pizzeria app.
 * It allows the user to view, modify, and place their order. The activity includes functionality
 * to display the order summary, remove pizzas, clear the order, and navigate back to the main menu.
 * The class interacts with the OrderManager to retrieve and update order details.
 */
public class CartActivity extends AppCompatActivity {

    /**
     * Tax rate constant used for calculating sales tax.
     */
    private static final double TAX_RATE = 0.06625;

    /**
     * DecimalFormat instance for formatting monetary values to two decimal places.
     */

    private DecimalFormat df = new DecimalFormat("0.00");

    /**
     * Singleton instance of the OrderManager for managing the current order.
     */
    private OrderManager orderManager;

    /**
     * Adapter for populating the ListView with pizza details.
     */
    private ArrayAdapter<String> orderAdapter;

    // UI components
    private EditText orderNumberDisplay;
    private EditText subtotalDisplay;
    private EditText salesTaxDisplay;
    private EditText orderTotalDisplay;
    private ListView orderDetailsDisplay;
    private Button backButton;
    private Button placeOrderButton;
    private Button removePizzaButton;
    private Button clearOrderButton;

    /**
     * Initializes the activity. Sets up the UI components, event listeners, and order summary.
     *
     * @param savedInstanceState The saved state of the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);

        orderManager = OrderManager.getInstance();

        initializeViews();
        setupListeners();
        updateOrderSummary();
    }

    /**
     * Initializes the views and UI components for the activity.
     * Sets up the order details ListView and disables editing for certain EditText fields.
     */
    private void initializeViews() {
        orderNumberDisplay = findViewById(R.id.order_number_display);
        subtotalDisplay = findViewById(R.id.subtotal_display);
        salesTaxDisplay = findViewById(R.id.sales_tax_display);
        orderTotalDisplay = findViewById(R.id.order_total_display);
        orderDetailsDisplay = findViewById(R.id.order_details_list);
        backButton = findViewById(R.id.back_button);
        placeOrderButton = findViewById(R.id.place_order_button);
        removePizzaButton = findViewById(R.id.remove_pizza_button);
        clearOrderButton = findViewById(R.id.clear_order_button);

        orderNumberDisplay.setKeyListener(null);
        subtotalDisplay.setKeyListener(null);
        salesTaxDisplay.setKeyListener(null);
        orderTotalDisplay.setKeyListener(null);

        orderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        orderDetailsDisplay.setAdapter(orderAdapter);
    }

    /**
     * Sets up event listeners for buttons and the ListView.
     * Defines actions for navigating back, placing orders, removing pizzas, and clearing the order.
     */
    private void setupListeners() {
        backButton.setOnClickListener(v -> navigateToMainMenu());
        placeOrderButton.setOnClickListener(v -> placeOrder());
        removePizzaButton.setOnClickListener(v -> removeSelectedPizza());
        clearOrderButton.setOnClickListener(v -> clearOrder());

        orderDetailsDisplay.setOnItemClickListener((parent, view, position, id) -> {
            for (int i = 0; i < orderDetailsDisplay.getCount(); i++) {
                orderDetailsDisplay.setItemChecked(i, false);
            }
            orderDetailsDisplay.setItemChecked(position, true);
        });
    }

    /**
     * Navigates back to the main menu activity and finishes the current activity.
     */
    private void navigateToMainMenu() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Places the current order. Displays an error if there are no pizzas in the order.
     * Updates the order summary upon successful placement.
     */
    private void placeOrder() {
        if (orderManager.getTotalPizzasInOrder() == 0) {
            showAlert("Error", "No pizzas in order to place.");
            return;
        }

        orderManager.placeOrder();
        showSuccess("Order Placed", "Your order has been placed successfully!");

        updateOrderSummary();
    }

    /**
     * Removes the currently selected pizza from the order. Displays an error if no pizza is selected.
     */
    private void removeSelectedPizza() {
        int selectedIndex = orderDetailsDisplay.getCheckedItemPosition();
        if (selectedIndex != AdapterView.INVALID_POSITION && orderManager.getTotalPizzasInOrder() > 0) {
            orderManager.removePizza(selectedIndex);
            updateOrderSummary();
        } else {
            showAlert("Error", "Please select a pizza to remove from the order.");
        }
    }

    /**
     * Clears all pizzas from the current order and updates the order summary.
     */
    private void clearOrder() {
        orderManager.clearOrderDisplay();
        updateOrderSummary();
    }

    /**
     * Updates the order summary, including the order details, subtotal, sales tax, and total cost.
     */
    private void updateOrderSummary() {
        orderAdapter.clear();

        orderNumberDisplay.setText(String.valueOf(orderManager.getCurrentOrderNumber()));

        for (Pizza pizza : orderManager.getPizzas()) {
            String pizzaDetails = formatPizzaDetails(pizza);
            orderAdapter.add(pizzaDetails);
        }

        double subtotal = orderManager.getSubtotal();
        double salesTax = orderManager.getTax();
        double total = orderManager.getTotal();

        subtotalDisplay.setText("$" + df.format(subtotal));
        salesTaxDisplay.setText("$" + df.format(salesTax));
        orderTotalDisplay.setText("$" + df.format(total));
    }

    /**
     * Formats the details of a pizza for display in the ListView.
     *
     * @param pizza The pizza to format.
     * @return A string representation of the pizza's details.
     */

    private String formatPizzaDetails(Pizza pizza) {
        return String.format("%s Pizza - %s, %s Crust\n" +
                        "Toppings: %s\n" +
                        "Price: $%.2f",
                pizza.getClass().getSimpleName(),
                pizza.getSize(),
                pizza.getCrust(),
                pizza.getToppings().stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(", ")),
                pizza.price());
    }

    /**
     * Displays an alert message to the user.
     *
     * @param title   The title of the alert.
     * @param message The message content of the alert.
     */
    private void showAlert(String title, String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Displays a success message to the user.
     *
     * @param title   The title of the success message.
     * @param message The message content of the success message.
     */
    private void showSuccess(String title, String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}