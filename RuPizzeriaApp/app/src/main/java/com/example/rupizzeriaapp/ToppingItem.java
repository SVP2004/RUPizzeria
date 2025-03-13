package com.example.rupizzeriaapp;

/**
 * Represents a topping item for a pizza with its associated details.
 * Includes the topping type, name, selection state, and image resource for display.
 */
public class ToppingItem {
    /**
     * The type of topping represented by this item.
     */
    public Topping topping;

    /**
     * The display name of the topping.
     */
    public String name;

    /**
     * Indicates whether the topping is currently selected.
     */
    public boolean isSelected;

    /**
     * The resource ID for the image associated with this topping.
     */
    public int imageResourceId;

    /**
     * Constructs a new {@code ToppingItem} with the specified topping details.
     * By default, the topping is not selected.
     *
     * @param topping         The type of topping represented by this item.
     * @param name            The display name of the topping.
     * @param imageResourceId The resource ID for the image representing the topping.
     */
    public ToppingItem(Topping topping, String name, int imageResourceId) {
        this.topping = topping;
        this.name = name;
        this.imageResourceId = imageResourceId;
        this.isSelected = false;
    }
}