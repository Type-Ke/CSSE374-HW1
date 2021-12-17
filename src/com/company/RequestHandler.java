package com.company;

import org.json.simple.JSONObject;

import java.util.Map;

public class RequestHandler {
    private Cart cart;
    private Address address;
    private int invalidDiscountCount;
    private DatabaseHandler databaseHandler;

    public RequestHandler(DatabaseHandler databaseHandler) {
        this.databaseHandler = databaseHandler;
        this.invalidDiscountCount = 0;
    }

    public JSONObject execute(JSONObject request) throws DummyDatabase.CartNotExistException, DatabaseHandler.DiscountTargetNotExistException, DummyDatabase.DiscountNotExistException, DummyDatabase.ItemNotExistException, DatabaseHandler.ItemOutOfStockException, DatabaseHandler.ItemNotInCartException, DatabaseHandler.NegativeQuantityException, TooManyInvalidCodesException, DatabaseHandler.DiscountItemMissingException, DatabaseHandler.DiscountExpiredException, InvalidRequestException {
        String work = (String) request.get("work");
        int cartId = (int) request.get("cartId");
        JSONObject address = (JSONObject) request.get("address");
        this.setAddress(address);
        if (work.equals("updateCart")) {
        } else if (work.equals("addToCart")) {
            int itemId = (int) request.get("itemId");
            int quantity = (int) request.get("quantity");
            this.handleAddToCart(cartId, itemId, quantity);
        } else if (work.equals("modifyCart")) {
            int itemId = (int) request.get("itemId");
            int quantity = (int) request.get("quantity");
            this.handleModifyCart(cartId, itemId, quantity);
        } else if (work.equals("applyDiscount")) {
            int discountCode = (int) request.get("discountCode");
            this.handleApplyDiscount(cartId, discountCode);
        } else {
            throw new InvalidRequestException("Request [" + work + "] is invalid");
        }
        this.retrieveUpdatedCart(cartId);
        return cart.encode(this.address);
    }

    public void setAddress(JSONObject address) {
        if ((boolean) address.get("valid")) this.address = new Address(true, (int) address.get("postCode"), (String) address.get("address"));
        else this.address = new Address(false, 0, null);
    }

    public void retrieveUpdatedCart(int cartId) throws DummyDatabase.DiscountNotExistException, DatabaseHandler.DiscountTargetNotExistException, DummyDatabase.CartNotExistException, DummyDatabase.ItemNotExistException {
        this.cart = this.databaseHandler.retrieveCart(cartId);
    }

    public void handleAddToCart(int cartId, int itemId, int quantity) throws DummyDatabase.CartNotExistException, DatabaseHandler.ItemOutOfStockException, DummyDatabase.ItemNotExistException {
        this.databaseHandler.addToCart(cartId, itemId, quantity);
    }

    public void handleModifyCart(int cartId, int itemId, int quantity) throws DummyDatabase.CartNotExistException, DatabaseHandler.NegativeQuantityException, DummyDatabase.ItemNotExistException, DatabaseHandler.ItemNotInCartException {
        this.databaseHandler.modifyCart(cartId, itemId, quantity);
    }

    public void handleApplyDiscount(int cartId, int discountCode) throws DummyDatabase.CartNotExistException, TooManyInvalidCodesException, DatabaseHandler.DiscountExpiredException, DummyDatabase.DiscountNotExistException, DatabaseHandler.DiscountItemMissingException {
        if (this.invalidDiscountCount > 5) throw new TooManyInvalidCodesException("More than five invalid discount codes has been attempted");
        try {
            this.databaseHandler.applyDiscount(cartId, discountCode);
        } catch (DatabaseHandler.DiscountExpiredException e) {
            this.invalidDiscountCount++;
            throw e;
        } catch (DummyDatabase.DiscountNotExistException e) {
            this.invalidDiscountCount++;
            throw e;
        } catch (DatabaseHandler.DiscountItemMissingException e) {
            this.invalidDiscountCount++;
            throw e;
        }
    }

    public class TooManyInvalidCodesException extends Exception {
        public TooManyInvalidCodesException(String errorMessage) {
            super(errorMessage);
        }
    }

    public class InvalidRequestException extends Exception {
        public InvalidRequestException(String errorMessage) {
            super(errorMessage);
        }
    }
}
