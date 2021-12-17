package com.company;

public class System {
    private Cart cart;
    private Address address;
    private int invalidDiscountCount;
    private DatabaseHandler databaseHandler;

    public System(DatabaseHandler databaseHandler) {
        this.databaseHandler = databaseHandler;
        this.invalidDiscountCount = 0;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setAddress(Address address) {
        this.address = address;
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
}
