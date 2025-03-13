package com.example.rupizzeriaapp;
/**
 * Represents a "Build Your Own" pizza where customers can select their own toppings.
 * The base price depends on the pizza size, and an additional charge is applied for each topping.
 * @author Sahil Patel, Shreyas Santosh
 */
public class BuildYourOwn extends Pizza {
    /**
     * The price for each additional topping.
     */
    private static final double TOPPING_PRICE = 1.69;

    /**
     * Calculates the total price of the "Build Your Own" pizza based on its size and the number of toppings.
     *
     * @return the total price of the pizza, which includes:
     *         - The base price ($8.99 for SMALL, $10.99 for MEDIUM, $12.99 for LARGE)
     *         - $1.69 per topping
     *         - $0.00 for an unspecified size
     */
    @Override
    public double price() {
        double basePrice;
        switch (getSize()) {
            case SMALL: basePrice = 8.99; break;
            case MEDIUM: basePrice = 10.99; break;
            case LARGE: basePrice = 12.99; break;
            default: basePrice = 0;
        }
        return basePrice + (getToppings().size() * TOPPING_PRICE);
    }
}