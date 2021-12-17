package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class DummyDatabase {
    private HashMap<Integer, ArrayList<Integer>> cartDiscountLists;
    private HashMap<Integer, HashMap<Integer, Integer>> cartItemLists;
    private HashMap<Integer, String> itemNames;
    private HashMap<Integer, String> itemDescriptions;
    private HashMap<Integer, Double> itemPrices;
    private HashMap<Integer, Double> itemDiscountRates;
    private HashMap<Integer, String> itemPictureLinks;
    private HashMap<Integer, Boolean> itemStocks;
    private HashMap<Integer, Boolean> discountSimpleChecks;
    private HashMap<Integer, Double> discountRates;
    private HashMap<Integer, Integer> simpleDiscountItems;
    private HashMap<Integer, ArrayList<Integer>> complexDiscountItemLists;
    private ArrayList<Integer> expiredDiscounts;

    public boolean discountExpired(int discountCode) {
        return expiredDiscounts.contains(discountCode);
    }

    public ArrayList<Integer> getCartDiscountList(int cartId) throws CartNotExistException {
        if (!cartDiscountLists.containsKey(cartId)) throw new CartNotExistException("Cart " + Integer.toString(cartId) + " does not exist.");
        return cartDiscountLists.get(cartId);
    }

    public HashMap<Integer, Integer> getCartItemList(int cartId) throws CartNotExistException {
        if (!cartItemLists.containsKey(cartId)) throw new CartNotExistException("Cart " + Integer.toString(cartId) + " does not exist.");
        return cartItemLists.get(cartId);
    }

    public String getItemName(int itemId) throws ItemNotExistException {
        if (!itemNames.containsKey(itemId)) throw new ItemNotExistException("Item " + Integer.toString(itemId) + " does not exist.");
        return itemNames.get(itemId);
    }

    public String getItemDescription(int itemId) throws ItemNotExistException {
        if (!itemDescriptions.containsKey(itemId)) throw new ItemNotExistException("Item " + Integer.toString(itemId) + " does not exist.");
        return itemDescriptions.get(itemId);
    }

    public Double getItemPrice(int itemId) throws ItemNotExistException {
        if (!itemPrices.containsKey(itemId)) throw new ItemNotExistException("Item " + Integer.toString(itemId) + " does not exist.");
        return itemPrices.get(itemId);
    }

    public Double getItemDiscountRate(int itemId) throws ItemNotExistException {
        if (!itemDiscountRates.containsKey(itemId)) throw new ItemNotExistException("Item " + Integer.toString(itemId) + " does not exist.");
        return itemDiscountRates.get(itemId);
    }

    public String getItemPictureLink(int itemId) throws ItemNotExistException {
        if (!itemPictureLinks.containsKey(itemId)) throw new ItemNotExistException("Item " + Integer.toString(itemId) + " does not exist.");
        return itemPictureLinks.get(itemId);
    }

    public Boolean getItemStock(int itemId) throws ItemNotExistException {
        if (!itemStocks.containsKey(itemId)) throw new ItemNotExistException("Item " + Integer.toString(itemId) + " does not exist.");
        return itemStocks.get(itemId);
    }

    public Boolean isSimple(int discountCode) throws DiscountNotExistException {
        if (!discountSimpleChecks.containsKey(discountCode)) throw new DiscountNotExistException("Discount " + Integer.toString(discountCode) + " does not exist.");
        return discountSimpleChecks.get(discountCode);
    }

    public Double getDiscountRate(int discountCode) throws DiscountNotExistException {
        if (!discountRates.containsKey(discountCode)) throw new DiscountNotExistException("Discount " + Integer.toString(discountCode) + " does not exist.");
        return discountRates.get(discountCode);
    }

    public Integer getSimpleDiscountItem(int discountCode) throws DiscountNotExistException {
        if (!simpleDiscountItems.containsKey(discountCode)) throw new DiscountNotExistException("Discount " + Integer.toString(discountCode) + " is not simple or does not exist.");
        return simpleDiscountItems.get(discountCode);
    }

    public ArrayList<Integer> getComplexDiscountItemList(int discountCode) throws DiscountNotExistException {
        if (!complexDiscountItemLists.containsKey(discountCode)) throw new DiscountNotExistException("Discount " + Integer.toString(discountCode) + " is not complex or does not exist.");
        return complexDiscountItemLists.get(discountCode);
    }

    public int getNumOfItemInCart(int cartId, int itemId) throws CartNotExistException {
        if (!cartItemLists.containsKey(cartId)) throw new CartNotExistException("Cart " + Integer.toString(cartId) + " does not exist.");
        if (cartItemLists.get(cartId).containsKey(itemId)) {
            return cartItemLists.get(cartId).get(itemId);
        }
        return 0;
    }

    public ArrayList<Integer> getRelatedDiscounts(int cartId, int itemId) {
        ArrayList<Integer> list = new ArrayList<>();
        for (Integer discountCode :
                cartDiscountLists.get(cartId)) {
            if (discountSimpleChecks.get(discountCode)) {
                if (simpleDiscountItems.get(discountCode) == itemId) list.add(discountCode);
            } else {
                if (complexDiscountItemLists.get(discountCode).contains(itemId)) list.add(discountCode);
            }
        }
        return list;
    }

    public void addItemToCart(int cartId, int itemId, int quantity) throws CartNotExistException {
        if (!cartItemLists.containsKey(cartId)) throw new CartNotExistException("Cart " + Integer.toString(cartId) + " does not exist.");
        this.cartItemLists.get(cartId).put(itemId, quantity);
    }

    public void modifyCart(int cartId, int itemId, int quantity) throws CartNotExistException {
        if (!cartItemLists.containsKey(cartId)) throw new CartNotExistException("Cart " + Integer.toString(cartId) + " does not exist.");
        this.cartItemLists.get(cartId).put(itemId, quantity);
    }

    public void removeItem(int cartId, int itemId) throws CartNotExistException {
        if (!cartItemLists.containsKey(cartId)) throw new CartNotExistException("Cart " + Integer.toString(cartId) + " does not exist.");
        cartItemLists.get(cartId).remove(itemId);
    }

    public void applyDiscount(int cartId, int discountCode) throws CartNotExistException {
        if (!cartDiscountLists.containsKey(cartId)) throw new CartNotExistException("Cart " + Integer.toString(cartId) + " does not exist.");
        this.cartDiscountLists.get(cartId).add(discountCode);
    }

    public void removeDiscount(int cartId, int discountCode) throws CartNotExistException {
        if (!cartDiscountLists.containsKey(cartId)) throw new CartNotExistException("Cart " + Integer.toString(cartId) + " does not exist.");
        this.cartDiscountLists.get(cartId).remove(discountCode);
    }

    public class CartNotExistException extends Exception {
        public CartNotExistException(String errorMessage) {
            super(errorMessage);
        }
    }

    public class ItemNotExistException extends Exception {
        public ItemNotExistException(String errorMessage) {
            super(errorMessage);
        }
    }

    public class DiscountNotExistException extends Exception {
        public DiscountNotExistException(String errorMessage) {
            super(errorMessage);
        }
    }
}
