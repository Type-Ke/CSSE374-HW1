package com.company;

import java.util.ArrayList;

public class ComplexDiscount extends Discount {
    private ArrayList<Item> items;
    private double rate;

    public ComplexDiscount(int code, ArrayList<Item> items, double rate) {
        super(code);
        this.items = items;
        this.rate = rate;
    }

    @Override
    public double calculate() {
        double sum = 0.0;
        for (Item item :
                this.items) {
            sum += this.rate * item.calculateCost();
        }
        return sum;
    }

    @Override
    public String encode() {
        return null;
    }
}
