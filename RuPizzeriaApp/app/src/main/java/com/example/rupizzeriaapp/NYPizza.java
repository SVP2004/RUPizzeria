package com.example.rupizzeriaapp;

/**
 * Represents a factory for creating New York-style pizzas.
 * Implements the {@link PizzaFactory} interface to provide specific variations of pizzas with New York-style crusts.
 * This includes Deluxe, Meatzza, BBQ Chicken, and Build Your Own pizzas.
 * @author Sahil Patel, Shreyas Santosh
 * <p>Each method initializes the respective pizza with a New York-style crust and appropriate default toppings.</p>
 *
 * @see PizzaFactory
 */
public class NYPizza implements PizzaFactory {


    /**
     * Creates a New York-style Deluxe pizza.
     *
     * @return a {@link Deluxe} pizza with:
     *         - New York Deluxe crust
     *         - Toppings: Sausage, Pepperoni, Green Pepper, Onion, Mushroom
     */
    @Override
    public Pizza createDeluxe() {
        Pizza pizza = new Deluxe();
        pizza.setCrust(Crust.NYDeluxe);
        pizza.addTopping(Topping.SAUSAGE);
        pizza.addTopping(Topping.PEPPERONI);
        pizza.addTopping(Topping.GREEN_PEPPER);
        pizza.addTopping(Topping.ONION);
        pizza.addTopping(Topping.MUSHROOM);
        return pizza;
    }

    /**
     * Creates a New York-style Meatzza pizza.
     *
     * @return a {@link Meatzza} pizza with:
     *         - New York Meatzza crust
     *         - Toppings: Sausage, Pepperoni, Beef, Ham
     */
    @Override
    public Pizza createMeatzza() {
        Pizza pizza = new Meatzza();
        pizza.setCrust(Crust.NYMeatzza);
        pizza.addTopping(Topping.SAUSAGE);
        pizza.addTopping(Topping.PEPPERONI);
        pizza.addTopping(Topping.BEEF);
        pizza.addTopping(Topping.HAM);
        return pizza;
    }

    /**
     * Creates a New York-style BBQ Chicken pizza.
     *
     * @return a {@link BBQChicken} pizza with:
     *         - New York BBQ Chicken crust
     *         - Toppings: BBQ Chicken, Green Pepper, Provolone, Cheddar
     */
    @Override
    public Pizza createBBQChicken() {
        Pizza pizza = new BBQChicken();
        pizza.setCrust(Crust.NYBBQChicken);
        pizza.addTopping(Topping.BBQ_CHICKEN);
        pizza.addTopping(Topping.GREEN_PEPPER);
        pizza.addTopping(Topping.PROVOLONE);
        pizza.addTopping(Topping.CHEDDAR);
        return pizza;
    }

    /**
     * Creates a New York-style Build Your Own pizza.
     *
     * @return a {@link BuildYourOwn} pizza with:
     *         - New York Build Your Own crust
     *         - No default toppings (toppings are user-defined)
     */
    @Override
    public Pizza createBuildYourOwn() {
        Pizza pizza = new BuildYourOwn();
        pizza.setCrust(Crust.NYBYO);
        return pizza;
    }
}
