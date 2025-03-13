package com.example.rupizzeriaapp;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a Deluxe pizza, a predefined type of pizza in the pizzeria menu.
 * The Deluxe pizza comes with specific toppings and has a set price based on its size.
 * @author Sahil Patel, Shreyas Santosh
 */
public class Deluxe extends Pizza {
    /**
     * Constructs a Deluxe pizza with predefined toppings:
     * Sausage, Pepperoni, Green Pepper, Onion, and Mushroom.
     */
    public Deluxe() {
        super();
        setToppings(new ArrayList<>(Arrays.asList(
                Topping.SAUSAGE, Topping.PEPPERONI, Topping.GREEN_PEPPER,
                Topping.ONION, Topping.MUSHROOM
        )));
    }

    /**
     * Calculates the price of the Deluxe pizza based on its size.
     *
     * @return the price of the pizza:
     *         - $16.99 for SMALL
     *         - $18.99 for MEDIUM
     *         - $20.99 for LARGE
     *         - $0.00 for an unspecified size
     */
    @Override
    public double price() {
        double basePrice;
        switch (getSize()) {
            case SMALL: basePrice = 16.99; break;
            case MEDIUM: basePrice = 18.99; break;
            case LARGE: basePrice = 20.99; break;
            default: basePrice = 0;
        }
        return basePrice;
    }
}
