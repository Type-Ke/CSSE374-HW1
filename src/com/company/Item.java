package com.company;

import org.json.simple.JSONObject;

public class Item {
    private int id;
    private ItemSpec spec;
    private int quantity;

    public Item(int id, ItemSpec spec, int quantity) {
        this.id = id;
        this.spec = spec;
        this.quantity = quantity;
    }

    double calculateCost() {
        return this.quantity * this.spec.getPriceWithDiscount();
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    JSONObject encode() {
        JSONObject encoding = this.spec.encode();
        encoding.put("Item id", this.id);
        encoding.put("Quantity", this.quantity);
        return encoding;
    }
}
