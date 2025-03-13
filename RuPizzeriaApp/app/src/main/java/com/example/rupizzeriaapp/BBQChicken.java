package com.example.rupizzeriaapp;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a BBQ Chicken pizza, a specific type of pizza in the pizzeria menu.
 * This pizza comes with predefined toppings and has a specific price based on its size.
 * @author Sahil Patel, Shreyas Santosh
 */
public class BBQChicken extends Pizza {
    /**
     * Constructs a BBQChicken pizza with predefined toppings:
     * BBQ Chicken, Green Pepper, Provolone, and Cheddar.
     */
    public BBQChicken() {
        super();
        setToppings(new ArrayList<>(Arrays.asList(
                Topping.BBQ_CHICKEN, Topping.GREEN_PEPPER,
                Topping.PROVOLONE, Topping.CHEDDAR
        )));
    }

    /**
     * Calculates the price of the BBQ Chicken pizza based on its size.
     *
     * @return the price of the pizza:
     *         - $14.99 for SMALL
     *         - $16.99 for MEDIUM
     *         - $19.99 for LARGE
     *         - $0.00 for an unspecified size
     */
    @Override
    public double price() {
        double basePrice;
        switch (getSize()) {
            case SMALL: basePrice = 14.99; break;
            case MEDIUM: basePrice = 16.99; break;
            case LARGE: basePrice = 19.99; break;
            default: basePrice = 0;
        }
        return basePrice;
    }
}

