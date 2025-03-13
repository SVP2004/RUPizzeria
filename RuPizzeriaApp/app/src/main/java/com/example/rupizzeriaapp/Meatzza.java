package com.example.rupizzeriaapp;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a Meatzza pizza, a predefined type of pizza in the pizzeria menu.
 * The Meatzza pizza features a variety of meat toppings and has a set price based on its size.
 * @author Sahil Patel, Shreyas Santosh
 */
public class Meatzza extends Pizza {
    /**
     * Constructs a Meatzza pizza with predefined toppings:
     * Sausage, Pepperoni, Beef, and Ham.
     */
    public Meatzza() {
        super();
        setToppings(new ArrayList<>(Arrays.asList(
                Topping.SAUSAGE, Topping.PEPPERONI,
                Topping.BEEF, Topping.HAM
        )));
    }

    /**
     * Calculates the price of the Meatzza pizza based on its size.
     *
     * @return the price of the pizza:
     *         - $17.99 for SMALL
     *         - $19.99 for MEDIUM
     *         - $21.99 for LARGE
     *         - $0.00 for an unspecified size
     */
    @Override
    public double price() {
        double basePrice;
        switch (getSize()) {
            case SMALL: basePrice = 17.99; break;
            case MEDIUM: basePrice = 19.99; break;
            case LARGE: basePrice = 21.99; break;
            default: basePrice = 0;
        }
        return basePrice;
    }
}