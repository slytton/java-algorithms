package com.galvanize.shoppingCart;

/**
 * Created by gschool on 6/21/16.
 */
public class CartItem {
    private Item item;
    private int quantity;

    public CartItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public double subTotal(){
        return this.item.getValue() * this.quantity;
    }

    public int addNItems(int n){
        return this.quantity += n;
    }

    public int removeNItems(int n){
        return this.quantity += n;
    }

    public Item getItem(){
        return this.item;
    }

    public int getQuantity(){
        return this.quantity;
    }
}
