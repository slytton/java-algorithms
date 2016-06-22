package com.galvanize.shoppingCart

import spock.lang.Specification

/**
 * Created by gschool on 6/21/16.
 */
class ShoppingCartTest extends Specification {
    def "ShoppingCart gets initialized without items or a total price" () {
        expect:
        new ShoppingCart().getItems().equals([]);
    }

    def "ShoppingCart allows you to add items to the cart." () {
        def fruit = new Item("Fruit", 4.50);
        def iceCream = new Item("Ice Cream", 5.50);

        def cart = new ShoppingCart();

        expect:
        cart.addItem(fruit)
        cart.getTotalPrice() == 4.5 as double;
        cart.addItem(iceCream);
        cart.getTotalPrice() == 10 as double;
    }

    def "ShoppingCart allows you to display an itemized list" () {
        def coffee = new Item("Coffee", 2.5)
        def donuts = new Item("Donuts", 4.5)

        def cart = new ShoppingCart();

        expect:
        cart.itemizedList().equals([])
        cart.addItem(coffee);
        cart.addItem(donuts);
        def list = cart.itemizedList();
        list.contains('Coffee - $2.5')
        list.contains('Donuts - $4.5')
        list.size() == 2
    }

    def "ShoppingCart allows you to add more than one of a given item to the cart" () {
        def fruit = new Item("Fruit", 4.50);
        def iceCream = new Item("Ice Cream", 5.50);

        def cart = new ShoppingCart();

        expect:
        cart.addItem(fruit, 2)
        cart.getTotalPrice() == 9 as double;
        cart.addItem(iceCream, 4);
        cart.getTotalPrice() == 31 as double;
    }

    def "ShoppingCart allows you to see how many of each item is in the cart" () {
        def coffee = new Item("Coffee", 1.50)
        def cream = new Item("Cream", 0.50)

        def cart = new ShoppingCart();

        expect:
        cart.itemQuantities().equals([])
        cart.addItem(coffee, 2);
        cart.itemQuantities().equals(['Coffee - 2'])
        cart.addItem(cream, 1);
        def quantities = cart.itemQuantities()
        quantities.contains('Coffee - 2') && quantities.contains('Cream - 1')
        quantities.size() == 2;

    }

    def "ShoppingCart allows you to display the names and prices of items that are on sale" () {
        def pie = new Item("Pie", 5.5, false);
        def coffee = new Item("Coffee", 2.5, true);
        def donuts = new Item("Donuts", 4.5, true);
        def cart = new ShoppingCart();
        cart.addItem(pie)

        expect:
        cart.onSaleItems().equals([])
        cart.addItem(coffee);
        cart.onSaleItems().containsAll(['Coffee - $2.5']);
        cart.addItem(donuts);
        cart.onSaleItems().containsAll(['Donuts - $4.5', 'Coffee - $2.5'])

    }
}

