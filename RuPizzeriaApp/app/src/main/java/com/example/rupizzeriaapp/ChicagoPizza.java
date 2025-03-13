package com.example.rupizzeriaapp;
/**
 * class for Chicago pizza object
 * @author Sahil Patel, Shreyas Santosh
 */
public class ChicagoPizza implements PizzaFactory{
    /**
     * Creates a Chicago-style Deluxe pizza.
     *
     * @return a {@link Deluxe} pizza with:
     *         - Chicago Deluxe crust
     *         - Toppings: Sausage, Pepperoni, Green Pepper, Onion, Mushroom
     */
    @Override
    public Pizza createDeluxe() {
        Pizza pizza = new Deluxe();
        pizza.setCrust(Crust.ChicagoDeluxe);
        pizza.addTopping(Topping.SAUSAGE);
        pizza.addTopping(Topping.PEPPERONI);
        pizza.addTopping(Topping.GREEN_PEPPER);
        pizza.addTopping(Topping.ONION);
        pizza.addTopping(Topping.MUSHROOM);
        return pizza;
    }

    /**
     * Creates a Chicago-style Meatzza pizza.
     *
     * @return a {@link Meatzza} pizza with:
     *         - Chicago Meatzza crust
     *         - Toppings: Sausage, Pepperoni, Beef, Ham
     */
    @Override
    public Pizza createMeatzza() {
        Pizza pizza = new Meatzza();
        pizza.setCrust(Crust.ChicagoMeatzza);
        pizza.addTopping(Topping.SAUSAGE);
        pizza.addTopping(Topping.PEPPERONI);
        pizza.addTopping(Topping.BEEF);
        pizza.addTopping(Topping.HAM);
        return pizza;
    }

    /**
     * Creates a Chicago-style BBQ Chicken pizza.
     *
     * @return a {@link BBQChicken} pizza with:
     *         - Chicago BBQ Chicken crust
     *         - Toppings: BBQ Chicken, Green Pepper, Provolone, Cheddar
     */
    @Override
    public Pizza createBBQChicken() {
        Pizza pizza = new BBQChicken();
        pizza.setCrust(Crust.ChicagoBBQChicken);
        pizza.addTopping(Topping.BBQ_CHICKEN);
        pizza.addTopping(Topping.GREEN_PEPPER);
        pizza.addTopping(Topping.PROVOLONE);
        pizza.addTopping(Topping.CHEDDAR);
        return pizza;
    }

    /**
     * Creates a Chicago-style Build Your Own pizza.
     *
     * @return a {@link BuildYourOwn} pizza with:
     *         - Chicago Build Your Own crust
     *         - No default toppings (toppings are user-defined)
     */
    @Override
    public Pizza createBuildYourOwn() {
        Pizza pizza = new BuildYourOwn();
        pizza.setCrust(Crust.ChicagoBYO);
        return pizza;
    }
}