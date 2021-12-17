package com.company;

import java.util.ArrayList;

public class Cart {
    private int id;
    private ArrayList<Item> items;
    private ArrayList<Discount> discounts;

    public Cart(int id) {
        this.id = id;
        this.items = new ArrayList<>();
        this.discounts = new ArrayList<>();
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void addDiscount(Discount discount) {
        this.discounts.add(discount);
    }

    public double sumCost() {
        double sum = 0.0;
        for (Item item :
                this.items) {
            sum += item.calculateCost();
        }
        return sum;
    }

    public double sumDiscounts() {
        double sum = 0.0;
        for (Discount discount :
                this.discounts) {
            sum += discount.calculate();
        }
        return sum;
    }

    public Item getItem(int itemId) throws ItemNotInCartException {
        for (Item item :
                this.items) {
            if (item.getId() == itemId) return item;
        }
        throw new ItemNotInCartException("Item " + Integer.toString(itemId) + " is not in cart " + Integer.toString(this.id));
    }

    public String encode(Address address) {
        return " **[Cart] need implementation** ";
    }

    public class ItemNotInCartException extends Exception {
        public ItemNotInCartException(String errorMessage) {
            super(errorMessage);
        }
    }

}
