package com.example.rupizzeriaapp;
/**
 * crust enum
 * @author Sahil Patel, Shreyas Santosh
 */
public enum Crust {


    ChicagoDeluxe ("Deep Dish"),
    NYDeluxe ("Brooklyn"),
    ChicagoBBQChicken("Pan"),
    NYBBQChicken("Thin"),
    ChicagoMeatzza ("Stuffed"),
    NYMeatzza ("Hand Tossed"),
    ChicagoBYO ("Pan"),
    NYBYO ("Hand Tossed");


    private String crust;

    /**
     * crust constructor
     * @param crust crust
     */
    Crust(String crust) {
        this.crust = crust;
    }

    /**
     * crust information
     * @param crust Crust
     * @return String
     */
    public static String crustInfo(Crust crust){
        return crust.crust;
    }

    /**
     * getCrust method to get the crust type
     * @param pizza Pizza as a string
     * @param type type as a string
     * @return crust
     */
    public static Crust getCrust(String pizza, String type){
        if (type.equalsIgnoreCase("New York")){
            if (pizza.equalsIgnoreCase("Deluxe")){
                return NYDeluxe;
            } else if (pizza.equalsIgnoreCase("Meatzza")){
                return NYMeatzza;
            } else if (pizza.equalsIgnoreCase("BBQ Chicken")){
                return NYBBQChicken;
            } else if (pizza.equalsIgnoreCase("Build Your Own")){
                return NYBYO;
            } else {
                return null;
            }
        } else if (type.equalsIgnoreCase("Chicago")){
            if (pizza.equalsIgnoreCase("Deluxe")){
                return ChicagoDeluxe;
            } else if (pizza.equalsIgnoreCase("Meatzza")){
                return ChicagoMeatzza;
            } else if (pizza.equalsIgnoreCase("BBQ Chicken")){
                return ChicagoBBQChicken;
            } else if (pizza.equalsIgnoreCase("Build Your Own")){
                return ChicagoBYO;
            } else {
                return null;
            }
        }
        return null;
    }

}
