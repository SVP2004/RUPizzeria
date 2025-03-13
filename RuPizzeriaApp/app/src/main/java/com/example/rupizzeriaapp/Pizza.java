package com.example.rupizzeriaapp;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents an abstract pizza with customizable toppings, crust, and size.
 * Subclasses of this class implement the {@link #price()} method to define pricing logic.
 * This class provides methods to manage pizza attributes such as toppings, crust, and size.
 * @author Sahil Patel, Shreyas Santosh
 */
public abstract class Pizza {
    private ArrayList<Topping> toppings;
    private Crust crust;
    private Size size;
    public abstract double price();

    /**
     * Constructs a pizza with an empty list of toppings.
     */
    public Pizza() {
        toppings = new ArrayList<>();
    }

    /**
     * Sets the crust type for the pizza.
     *
     * @param crust the {@link Crust} to set
     */
    public void setCrust(Crust crust) {
        this.crust = crust;
    }

    /**
     * Sets the size of the pizza.
     *
     * @param size the {@link Size} to set
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * Sets the list of toppings for the pizza.
     *
     * @param toppings an {@link ArrayList} of {@link Topping} objects
     */
    public void setToppings(ArrayList<Topping> toppings) {
        this.toppings = toppings;
    }

    /**
     * Retrieves the list of toppings on the pizza.
     *
     * @return an {@link ArrayList} of {@link Topping} objects
     */
    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    /**
     * Retrieves the crust type of the pizza.
     *
     * @return the {@link Crust} of the pizza
     */
    public Crust getCrust() {
        return crust;
    }

    /**
     * Retrieves the size of the pizza.
     *
     * @return the {@link Size} of the pizza
     */
    public Size getSize() {
        return size;
    }

    /**
     * Adds a topping to the pizza.
     * The maximum number of toppings is limited to 7.
     *
     * @param topping the {@link Topping} to add
     */
    public void addTopping(Topping topping) {
        if (toppings.size() < 7) {
            toppings.add(topping);
        }
    }


    /**
     * Provides a string representation of the pizza, including:
     * - Pizza type
     * - Size
     * - Crust type
     * - List of toppings
     * - Price
     *
     * @return a formatted string describing the pizza
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName())
                .append(" (").append(size).append(") ")
                .append("[").append(crust).append("] ")
                .append("Toppings: ");
        for (Topping topping : toppings) {
            sb.append(topping.toString().toLowerCase().replace('_', ' ')).append(", ");
        }
        if (!toppings.isEmpty()) {
            sb.setLength(sb.length() - 2);
        }
        sb.append(" $").append(String.format("%.2f", price()));
        return sb.toString();
    }
}