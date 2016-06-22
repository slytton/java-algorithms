package com.galvanize.shoppingCart;

/**
 * Created by gschool on 6/21/16.
 */
public class Item {
    private String name;
    private double value;
    private int quantity;
    private boolean onSale;
    public Item(String name, double value) {
        this.name = name;
        this.value = value;
        this.onSale = false;
    }

    public Item(String name, double value, boolean onSale) {
        this.name = name;
        this.value = value;
        this.onSale = onSale;
    }

    public double getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public boolean isOnSale(){
        return this.onSale;
    }
}

