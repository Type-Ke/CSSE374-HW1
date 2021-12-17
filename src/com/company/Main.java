package com.company;

import org.json.simple.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws DummyDatabase.DiscountNotExistException, DatabaseHandler.DiscountTargetNotExistException, DummyDatabase.CartNotExistException, DummyDatabase.ItemNotExistException, DatabaseHandler.ItemNotInCartException, DatabaseHandler.NegativeQuantityException, DatabaseHandler.DiscountExpiredException, DatabaseHandler.ItemOutOfStockException, RequestHandler.InvalidRequestException, RequestHandler.TooManyInvalidCodesException, DatabaseHandler.DiscountItemMissingException {
        DummyDatabase testDummy = new DummyDatabase();
        DatabaseHandler handler = new DatabaseHandler(testDummy);
        RequestHandler sys = new RequestHandler(handler);


        JSONObject request = new JSONObject();
        request.put("work", "updateCart");
        request.put("cartId", 24681);
        JSONObject address = new JSONObject();
        address.put("valid", true);
        address.put("address", "21 2nd Street, New York, NY");
        address.put("postCode", 10021);
        request.put("address", address);

        JSONObject result = sys.execute(request);
        System.out.println(result.toString());


        request = new JSONObject();
        request.put("work", "addToCart");
        request.put("cartId", 24681);
        request.put("itemId", 103);
        request.put("quantity", 20);
        address = new JSONObject();
        address.put("valid", false);
        address.put("address", "21 2nd Street, New York, NY");
        address.put("postCode", 10021);
        request.put("address", address);

        result = sys.execute(request);
        System.out.println(result.toString());


        request = new JSONObject();
        request.put("work", "modifyCart");
        request.put("cartId", 24681);
        request.put("itemId", 101);
        request.put("quantity", 50);
        address = new JSONObject();
        address.put("valid", true);
        address.put("address", "21 2nd Street, New York, NY");
        address.put("postCode", 10021);
        request.put("address", address);

        result = sys.execute(request);
        System.out.println(result.toString());

        request = new JSONObject();
        request.put("work", "modifyCart");
        request.put("cartId", 24681);
        request.put("itemId", 103);
        request.put("quantity", 0);
        address = new JSONObject();
        address.put("valid", true);
        address.put("address", "21 2nd Street, New York, NY");
        address.put("postCode", 10021);
        request.put("address", address);

        result = sys.execute(request);
        System.out.println(result.toString());


        request = new JSONObject();
        request.put("work", "addToCart");
        request.put("cartId", 24681);
        request.put("itemId", 104);
        request.put("quantity", 50);
        address = new JSONObject();
        address.put("valid", false);
        address.put("address", "21 2nd Street, New York, NY");
        address.put("postCode", 10021);
        request.put("address", address);

        result = sys.execute(request);
        System.out.println(result.toString());


        request = new JSONObject();
        request.put("work", "applyDiscount");
        request.put("cartId", 24681);
        request.put("discountCode", 1015);
        address = new JSONObject();
        address.put("valid", false);
        address.put("address", "21 2nd Street, New York, NY");
        address.put("postCode", 10021);
        request.put("address", address);

        result = sys.execute(request);
        System.out.println(result.toString());

    }
}
