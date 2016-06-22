package com.galvanize.shoppingCart;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by gschool on 6/21/16.
 */
public class ShoppingCart {
    private HashMap<Item, CartItem> items;
    public ShoppingCart(){
        this.items = new HashMap<>();
    }

    public double getTotalPrice() {
        double total = 0;
        for(CartItem cartItem: this.items.values()){
            total += cartItem.subTotal();
        }
        return total;
    }

    public void addItem(Item item){
        CartItem cartItem = this.items.get(item);
        if(cartItem == null) this.items.put(item, new CartItem(item, 1));
        else cartItem.addNItems(1);
    }

    public void addItem(Item item, int quantity){
        CartItem cartItem = this.items.get(item);
        if(cartItem == null) this.items.put(item, new CartItem(item, quantity));
        else cartItem.addNItems(quantity);
    }

    public ArrayList<Item> getItems() {
        return new ArrayList<>(this.items.keySet());
    }

    public String[] itemizedList() {
        String[] result = new String[this.items.size()];
        int i = 0;
        for(Item item: this.items.keySet()){
            result[i] = item.getName() + " - " + "$" + item.getValue();
            i++;
        }

        return result;
    }

    public String[] itemQuantities(){
        String[] result = new String[this.items.size()];
        int i = 0;
        for (CartItem item : this.items.values()) {
            result[i] = item.getItem().getName() + " - " + item.getQuantity();
            i++;
        }
        return result;
    }

    public ArrayList<String> onSaleItems(){
        ArrayList<String> result = new ArrayList<>();
        for(Item item: this.items.keySet()){
            if(item.isOnSale()) result.add(item.getName() + " - " + "$" + item.getValue());
        }
        return result;
    }
}
