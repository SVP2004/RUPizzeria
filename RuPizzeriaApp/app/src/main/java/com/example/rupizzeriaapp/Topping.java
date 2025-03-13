package com.example.rupizzeriaapp;

import java.util.ArrayList;

/**
 * Enum representing the various toppings available for pizzas.
 * This enum includes utility methods to manage predefined sets of toppings for specific pizza types.
 * @author Sahil Patel, Shreyas Santosh
 */
public enum Topping {
    SAUSAGE,
    PEPPERONI,
    GREEN_PEPPER,
    ONION,
    MUSHROOM,
    BBQ_CHICKEN,
    CHEDDAR,
    PROVOLONE,
    BEEF,
    HAM,
    OLIVES,
    PINEAPPLES,
    JALAPENOS,
    PLAIN;


    /**
     * Retrieves a list of toppings based on a predefined pizza type.
     *
     * @param type the name of the pizza type (e.g., "Deluxe", "BBQ Chicken", "Meatzza")
     * @return an {@link ArrayList} of {@link Topping} objects corresponding to the pizza type,
     *         or an empty list if the type is unrecognized.
     */
    public static ArrayList<Topping> getToppings(String type){
        ArrayList<Topping> toppings = new ArrayList<>();
        if (type.equalsIgnoreCase("Deluxe")){
            toppings.add(SAUSAGE);
            toppings.add(PEPPERONI);
            toppings.add(GREEN_PEPPER);
            toppings.add(ONION);
            toppings.add(MUSHROOM);
        } else if (type.equalsIgnoreCase("BBQ Chicken")){
            toppings.add(BBQ_CHICKEN);
            toppings.add(GREEN_PEPPER);
            toppings.add(PROVOLONE);
            toppings.add(CHEDDAR);
        } else if (type.equalsIgnoreCase("Meatzza")){
            toppings.add(SAUSAGE);
            toppings.add(PEPPERONI);
            toppings.add(BEEF);
            toppings.add(HAM);
        }
        return toppings;
    }
}
