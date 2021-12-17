package com.company;

public class Item {
    private int id;
    private ItemSpec spec;
    private int numInCart;

    public Item(int id, ItemSpec spec, int numInCart) {
        this.id = id;
        this.spec = spec;
        this.numInCart = numInCart;
    }

    double calculateCost() {
        return this.numInCart * this.spec.getPriceWithDiscount();
    }

    public int getId() {
        return id;
    }

    String encode() {
        return this.spec.encode() + " **[Item] need implementation** ";
    }
}
