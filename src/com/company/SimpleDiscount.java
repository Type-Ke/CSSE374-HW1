package com.company;

public class SimpleDiscount extends Discount {
    private Item item;
    private double rate;

    public SimpleDiscount(int code, Item item, double rate) {
        super(code);
        this.item = item;
        this.rate = rate;
    }

    @Override
    public double calculate() {
        return this.rate * this.item.calculateCost();
    }

    @Override
    public String encode() {
        return " **[SimpleDiscount] need implementation** ";
    }
}
