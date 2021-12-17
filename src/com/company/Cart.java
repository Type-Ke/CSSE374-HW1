package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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

    public JSONObject encode(Address address) {
        JSONObject encoding = new JSONObject();
        encoding.put("Cart id", this.id);
        int numCount = 0;
        for (Item item :
                this.items) {
            numCount += item.getQuantity();
        }
        encoding.put("Total number", numCount);
        JSONArray itemList = new JSONArray();
        for (Item item :
                this.items) {
            itemList.add(item.encode());
        }
        encoding.put("Items", itemList);
        double costSum = this.sumCost();
        double discountsSum = this.sumDiscounts();
        encoding.put("Sum of discounts", discountsSum);
        JSONObject costs = new JSONObject();
        costs.put("Cost of items", costSum);
        costs.put("Discounts", discountsSum);
        try {
            costs.put("Estimated taxes", address.calculateTax(costSum));
        } catch (Address.TaxCalculationNotAvailableException e) {
            costs.put("Estimated taxes", "Tex calculation not available.");
        } catch (Address.InvalidAddressException e) {
            costs.put("Estimated taxes", "Invalid address.");
        }
        encoding.put("Total costs", costs);
        return encoding;
    }

    public class ItemNotInCartException extends Exception {
        public ItemNotInCartException(String errorMessage) {
            super(errorMessage);
        }
    }

}
