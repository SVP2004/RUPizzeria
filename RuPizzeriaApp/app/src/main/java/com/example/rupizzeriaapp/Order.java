package com.example.rupizzeriaapp;



import java.util.ArrayList;

/**
 * Represents an order in the pizzeria system.
 * Each order contains a unique order number, a list of pizzas, and provides methods to calculate
 * subtotal, tax, and total cost. Orders can be placed and tracked in a static list of store orders.
 * @author Sahil Patel, Shreyas Santosh
 */
public class Order {
    private static int nextOrderNumber = 1;
    private int orderNumber;
    private ArrayList<Pizza> pizzas;
    private static final double TAX_RATE = 0.06625;
    private static ArrayList<Order> storeOrders = new ArrayList<>();

    /**
     * Constructs an Order with a unique order number and initializes an empty list of pizzas.
     */
    public Order() {
        this.orderNumber = nextOrderNumber++;
        this.pizzas = new ArrayList<>();
    }

    /**
     * Retrieves the unique order number of this order.
     *
     * @return the order number
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * Adds a pizza to this order.
     *
     * @param pizza the {@link Pizza} to add
     * @return true if the pizza was successfully added, false otherwise
     */
    public boolean addPizza(Pizza pizza) {
        if(pizza != null) {
            pizzas.add(pizza);
            return true;
        }
        return false;
    }


    /**
     * Removes a pizza from this order based on its index.
     *
     * @param index the index of the pizza to remove
     * @return true if the pizza was successfully removed, false otherwise
     */
    public boolean removePizza(int index) {
        if(index < pizzas.size() && index >= 0) {
            pizzas.remove(index);
            return true;
        }
        return false;
    }

    /**
     * Retrieves the list of pizzas in this order.
     *
     * @return an {@link ArrayList} of pizzas
     */
    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    /**
     * Calculates the subtotal of the order by summing up the prices of all pizzas.
     *
     * @return the subtotal as a double
     */
    public double getSubtotal() {
        double subtotal = 0;
        for (Pizza pizza : pizzas) {
            subtotal += pizza.price();
        }
        return subtotal;
    }


    /**
     * Calculates the tax for the order based on the subtotal and tax rate.
     *
     * @return the tax amount as a double
     */
    public double getTax() {
        return getSubtotal() * TAX_RATE;
    }

    /**
     * Calculates the total cost of the order, including tax.
     *
     * @return the total amount as a double
     */
    public double getTotal() {
        return getSubtotal() + getTax();
    }

    /**
     * Places the order, adding it to the static list of all store orders.
     * Ensures the order is not added multiple times.
     */
    public void placeOrder() {
        if (!storeOrders.contains(this)) {
            storeOrders.add(this);
        } else {
            return;
        }
    }

    /**
     * Retrieves a list of all placed orders.
     *
     * @return an {@link ArrayList} of all orders
     */
    public static ArrayList<Order>getAllOrders(){
        return storeOrders;
    }

    /**
     * Clears the list of pizzas in this order, resetting the order's contents.
     */
    public void clearOrderDisplay() {
        pizzas.clear();
    }



    /**
     * Returns a string representation of the order, including the order number,
     * details of each pizza, and the subtotal, tax, and total amounts.
     *
     * @return a string summarizing the order
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order #").append(orderNumber).append("\n");
        for (Pizza pizza : pizzas) {
            sb.append(pizza.toString()).append("\n");
        }
        sb.append(String.format("Subtotal: $%.2f\n", getSubtotal()));
        sb.append(String.format("Tax: $%.2f\n", getTax()));
        sb.append(String.format("Total: $%.2f", getTotal()));
        return sb.toString();
    }
}

