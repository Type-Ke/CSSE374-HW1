package com.company;

public class ItemSpec {
    private String name;
    private String description;
    private double price;
    private double discountRate;
    private String pictureLink;
    private boolean inStock;

    public ItemSpec(String name, String description, double price, double discountRate, String pictureLink, boolean inStock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.discountRate = discountRate;
        this.pictureLink = pictureLink;
        this.inStock = inStock;
    }

    public boolean isInStock() {
        return inStock;
    }

    public double getPriceWithDiscount() {
        return price * (1.0 - this.discountRate);
    }

    public String encode() {
        return " **[ItemSpec] need implementation** ";
    }

}
