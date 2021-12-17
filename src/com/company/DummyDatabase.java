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

    public DummyDatabase() {
        this.itemNames = new HashMap<>();
        this.itemDescriptions = new HashMap<>();
        this.itemPrices = new HashMap<>();
        this.itemDiscountRates = new HashMap<>();
        this.itemPictureLinks = new HashMap<>();
        this.itemStocks = new HashMap<>();

        this.itemNames.put(101, "CocaCola");
        this.itemDescriptions.put(101, "CocaCola's best selling product.");
        this.itemPrices.put(101, 2.0);
        this.itemDiscountRates.put(101, 0.25);
        this.itemPictureLinks.put(101, "http://cocacola.png");
        this.itemStocks.put(101, true);

        this.itemNames.put(102, "Dr. Pepper");
        this.itemDescriptions.put(102, "A strange kind of drink.");
        this.itemPrices.put(102, 2.0);
        this.itemDiscountRates.put(102, 0.0);
        this.itemPictureLinks.put(102, "http://drpepper.png");
        this.itemStocks.put(102, false);

        this.itemNames.put(103, "Arizona Tea");
        this.itemDescriptions.put(103, "Sweet tea. Very good tasting.");
        this.itemPrices.put(103, 4.0);
        this.itemDiscountRates.put(103, 0.125);
        this.itemPictureLinks.put(103, "http://arizonatea.png");
        this.itemStocks.put(103, true);

        this.itemNames.put(104, "Sprite");
        this.itemDescriptions.put(104, "CocaCola's second best selling product.");
        this.itemPrices.put(104, 2.0);
        this.itemDiscountRates.put(104, 0.0);
        this.itemPictureLinks.put(104, "http://sprite.png");
        this.itemStocks.put(104, true);


        this.discountSimpleChecks = new HashMap<>();
        this.discountRates = new HashMap<>();
        this.simpleDiscountItems = new HashMap<>();
        this.complexDiscountItemLists = new HashMap<>();
        this.expiredDiscounts = new ArrayList<>();

        this.discountSimpleChecks.put(1011, true);
        this.discountRates.put(1011, 0.5);
        this.simpleDiscountItems.put(1011, 101);

        this.discountSimpleChecks.put(1012, true);
        this.discountRates.put(1012, 0.25);
        this.simpleDiscountItems.put(1012, 102);

        this.discountSimpleChecks.put(1013, true);
        this.discountRates.put(1013, 0.1);
        this.simpleDiscountItems.put(1013, 103);

        this.discountSimpleChecks.put(1014, true);
        this.discountRates.put(1014, 0.25);
        this.simpleDiscountItems.put(1014, 104);

        this.discountSimpleChecks.put(1015, false);
        this.discountRates.put(1015, 0.5);
        ArrayList<Integer> itemList1 = new ArrayList<>();
        itemList1.add(101);
        itemList1.add(104);
        this.complexDiscountItemLists.put(1015, itemList1);

        this.discountSimpleChecks.put(1016, false);
        this.discountRates.put(1016, 0.5);
        ArrayList<Integer> itemList2 = new ArrayList<>();
        itemList2.add(102);
        itemList2.add(103);
        this.complexDiscountItemLists.put(1016, itemList2);

        this.expiredDiscounts.add(1014);


        this.cartItemLists = new HashMap<>();
        this.cartDiscountLists = new HashMap<>();

        HashMap<Integer, Integer> cartList1 = new HashMap<>();
        cartList1.put(101, 10);
        this.cartItemLists.put(24681, cartList1);
        ArrayList<Integer> discountList1 = new ArrayList<>();
        discountList1.add(1011);
        this.cartDiscountLists.put(24681, discountList1);
    }


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
