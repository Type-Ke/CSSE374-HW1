package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHandler {
    private DummyDatabase dummyDatabase;

    public DatabaseHandler(DummyDatabase dummyDatabase) {
        this.dummyDatabase = dummyDatabase;
    }

    public ItemSpec retrieveItemSpec(int itemId) throws DummyDatabase.ItemNotExistException {
        String name = this.dummyDatabase.getItemName(itemId);
        String description = this.dummyDatabase.getItemDescription(itemId);
        double price = this.dummyDatabase.getItemPrice(itemId);
        double discountRate = this.dummyDatabase.getItemDiscountRate(itemId);
        String pictureLink = this.dummyDatabase.getItemPictureLink(itemId);
        boolean inStock = this.dummyDatabase.getItemStock(itemId);
        return new ItemSpec(name, description, price, discountRate, pictureLink, inStock);
    }

    public Discount retrieveDiscount(Cart cart, int discountCode) throws DummyDatabase.DiscountNotExistException, DiscountTargetNotExistException {
        if (this.dummyDatabase.isSimple(discountCode)) {
            int itemId = this.dummyDatabase.getSimpleDiscountItem(discountCode);
            Item item = null;
            try {
                item = cart.getItem(itemId);
            } catch (Cart.ItemNotInCartException e) {
                throw new DiscountTargetNotExistException("Target of discount " + Integer.toString(discountCode) + " is not in the cart.");
            }
            double rate = this.dummyDatabase.getDiscountRate(discountCode);
            return new SimpleDiscount(discountCode, item, rate);
        }
        ArrayList<Integer> itemIds = this.dummyDatabase.getComplexDiscountItemList(discountCode);
        ArrayList<Item> items = new ArrayList<>();
        for (Integer id :
                itemIds) {
            try {
                items.add(cart.getItem(id));
            } catch (Cart.ItemNotInCartException e) {
                throw new DiscountTargetNotExistException("Target of discount " + Integer.toString(discountCode) + " is not in the cart.");
            }
        }
        double rate = this.dummyDatabase.getDiscountRate(discountCode);
        return new ComplexDiscount(discountCode, items, rate);
    }

    public Cart retrieveCart(int cartId) throws DummyDatabase.CartNotExistException, DummyDatabase.ItemNotExistException, DummyDatabase.DiscountNotExistException, DiscountTargetNotExistException {
        Cart cart = new Cart(cartId);
        HashMap<Integer, Integer> itemIdList = this.dummyDatabase.getCartItemList(cartId);
        for (Integer itemId :
                itemIdList.keySet()) {
            ItemSpec spec = this.retrieveItemSpec(itemId);
            cart.addItem(new Item(itemId, spec, itemIdList.get(itemId)));
        }
        ArrayList<Integer> discountCodeList = this.dummyDatabase.getCartDiscountList(cartId);
        for (Integer discountCode :
                discountCodeList) {
            cart.addDiscount(this.retrieveDiscount(cart, discountCode));
        }
        return cart;
    }

    public int retrieveNumOfItemInCart(int cartId, int itemId) throws DummyDatabase.CartNotExistException {
        return this.dummyDatabase.getNumOfItemInCart(cartId, itemId);
    }

    public void addToCart(int cartId, int itemId, int quantity) throws DummyDatabase.ItemNotExistException, DummyDatabase.CartNotExistException, ItemOutOfStockException {
        if (this.dummyDatabase.getItemStock(itemId)) {
            this.dummyDatabase.addItemToCart(cartId, itemId, quantity);
            return;
        }
        throw new ItemOutOfStockException("Item " + Integer.toString(itemId) + " is out of stock");
    }

    public void modifyCart(int cartId, int itemId, int desiredQuantity) throws NegativeQuantityException, DummyDatabase.CartNotExistException, ItemNotInCartException, DummyDatabase.ItemNotExistException {
        if (desiredQuantity < 0) throw new NegativeQuantityException("Negative desired quantity");
        if (retrieveNumOfItemInCart(cartId, itemId) == 0) throw new ItemNotInCartException("Item " + Integer.toString(itemId) + " is not in cart: " + Integer.toString(cartId));
        if (desiredQuantity == 0) {
            this.dummyDatabase.removeItem(cartId, itemId);
            for (Integer discountCode :
                    this.dummyDatabase.getRelatedDiscounts(cartId, itemId)) {
                this.dummyDatabase.removeDiscount(cartId, discountCode);
            }
            return;
        }
        this.dummyDatabase.modifyCart(cartId,itemId,desiredQuantity);
    }

    public void applyDiscount(int cartId, int discountCode) throws DiscountExpiredException, DummyDatabase.DiscountNotExistException, DummyDatabase.CartNotExistException, DiscountItemMissingException {
        if (this.dummyDatabase.discountExpired(discountCode)) throw new DiscountExpiredException("Discount code " + Integer.toString(discountCode) + " has expired.");
        if (this.dummyDatabase.isSimple(discountCode)) {
            int itemId = this.dummyDatabase.getSimpleDiscountItem(discountCode);
            if (this.dummyDatabase.getNumOfItemInCart(cartId, itemId) == 0) throw new DiscountItemMissingException("Target item(s) for discount " + Integer.toString(discountCode) + " isn't in the cart");
        } else {
            ArrayList<Integer> itemIds = this.dummyDatabase.getComplexDiscountItemList(discountCode);
            for (Integer itemId :
                    itemIds) {
                if (this.dummyDatabase.getNumOfItemInCart(cartId, itemId) == 0) throw new DiscountItemMissingException("Target item(s) for discount " + Integer.toString(discountCode) + " isn't in the cart");
            }
        }
        this.dummyDatabase.applyDiscount(cartId, discountCode);
    }

    public class DiscountTargetNotExistException extends Exception {
        public DiscountTargetNotExistException(String errorMessage) {
            super(errorMessage);
        }
    }

    public class ItemOutOfStockException extends Exception {
        public ItemOutOfStockException(String errorMessage) {
            super(errorMessage);
        }
    }

    public class NegativeQuantityException extends Exception {
        public NegativeQuantityException(String errorMessage) {
            super(errorMessage);
        }
    }

    public class ItemNotInCartException extends Exception {
        public ItemNotInCartException(String errorMessage) {
            super(errorMessage);
        }
    }

    public class DiscountExpiredException extends Exception {
        public DiscountExpiredException(String errorMessage) {
            super(errorMessage);
        }
    }

    public class DiscountItemMissingException extends Exception {
        public DiscountItemMissingException(String errorMessage) {
            super(errorMessage);
        }
    }

}
