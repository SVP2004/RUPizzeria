package com.example.rupizzeriaapp;

/**
 * Factory interface for creating various types of pizzas.
 * Implementing classes define the specifics of how each type of pizza is created.
 * @author Sahil Patel, Shreyas Santosh
 */

public interface PizzaFactory {
    /**
     * Creates a Deluxe pizza with predefined toppings and crust specific to the factory's style.
     *
     * @return a {@link Pizza} object representing a Deluxe pizza
     */
    Pizza createDeluxe();

    /**
     * Creates a Meatzza pizza with predefined toppings and crust specific to the factory's style.
     *
     * @return a {@link Pizza} object representing a Meatzza pizza
     */
    Pizza createMeatzza();

    /**
     * Creates a BBQ Chicken pizza with predefined toppings and crust specific to the factory's style.
     *
     * @return a {@link Pizza} object representing a BBQ Chicken pizza
     */
    Pizza createBBQChicken();

    /**
     * Creates a customizable Build Your Own pizza with a default crust specific to the factory's style.
     *
     * @return a {@link Pizza} object representing a Build Your Own pizza
     */
    Pizza createBuildYourOwn();
}
