package com.company;

public abstract class Discount {
    private int code;

    public Discount(int code) {
        this.code = code;
    }

    public abstract double calculate();
    public abstract String encode();
}
