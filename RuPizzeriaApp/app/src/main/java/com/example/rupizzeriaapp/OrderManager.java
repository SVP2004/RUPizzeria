package com.example.rupizzeriaapp;

import java.util.ArrayList;

public class OrderManager {
    // Singleton instance
    private static OrderManager instance;

    private Order currentOrder;

    private OrderManager() {
        currentOrder = new Order();
    }

    /**
     * Provides global access to the singleton instance
     * Uses synchronized to ensure thread safety
     *
     * @return the singleton instance of OrderManager
     */
    public static synchronized OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }

    /**
     * Adds a pizza to the current order
     *
     * @param pizza the pizza to be added
     * @return true if pizza was successfully added, false otherwise
     */
    public boolean addPizza(Pizza pizza) {
        return currentOrder.addPizza(pizza);
    }

    /**
     * Retrieves the current order
     *
     * @return the current Order object
     */
    public Order getCurrentOrder() {
        return currentOrder;
    }

    /**
     * Gets the total number of pizzas in the current order
     *
     * @return number of pizzas in the current order
     */
    public int getTotalPizzasInOrder() {
        return currentOrder.getPizzas().size();
    }

    /**
     * Clears the pizzas in the current order
     */
    public void clearOrderDisplay() {
        currentOrder.clearOrderDisplay();
    }

    /**
     * Places the current order
     */
    public void placeOrder() {
        currentOrder.placeOrder();
        // Create a new order for subsequent additions
        currentOrder = new Order();
    }

    /**
     * Removes a pizza from the current order by index
     *
     * @param index the index of the pizza to remove
     * @return true if pizza was successfully removed, false otherwise
     */
    public boolean removePizza(int index) {
        return currentOrder.removePizza(index);
    }

    /**
     * Retrieves all pizzas in the current order
     *
     * @return ArrayList of pizzas in the current order
     */
    public ArrayList<Pizza> getPizzas() {
        return currentOrder.getPizzas();
    }

    /**
     * Calculates the subtotal of the current order
     *
     * @return subtotal amount
     */
    public double getSubtotal() {
        return currentOrder.getSubtotal();
    }

    /**
     * Calculates the tax for the current order
     *
     * @return tax amount
     */
    public double getTax() {
        return currentOrder.getTax();
    }

    /**
     * Calculates the total cost of the current order
     *
     * @return total amount
     */
    public double getTotal() {
        return currentOrder.getTotal();
    }

    /**
     * Retrieves all store orders
     *
     * @return ArrayList of all orders
     */
    public static ArrayList<Order> getAllOrders() {
        return Order.getAllOrders();
    }

    /**
     * Gets the order number of the current order
     *
     * @return order number
     */
    public int getCurrentOrderNumber() {
        return currentOrder.getOrderNumber();
    }
}