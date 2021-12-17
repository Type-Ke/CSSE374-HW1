package com.company;

import org.json.simple.JSONObject;

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

    public JSONObject encode() {
        JSONObject encoding = new JSONObject();
        encoding.put("Name", this.name);
        encoding.put("Description", this.description);
        encoding.put("Price", this.price);
        encoding.put("Picture", this.pictureLink);
        encoding.put("In Stock?", this.inStock);
        return encoding;
    }

}
