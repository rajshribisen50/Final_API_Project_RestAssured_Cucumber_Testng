package utils;

import java.util.ArrayList;
import java.util.List;

public class ScenarioContext {

    private static final List<Integer> userIds = new ArrayList<>();
    private static final List<Integer> cartIds = new ArrayList<>();

    // USER
    public static void addUserId(int id) {
        userIds.add(id);
    }

    public static Integer[] getUserIds() {
        return userIds.toArray(new Integer[0]);
    }

    // CART
    public static void addCartId(int id) {
        cartIds.add(id);
    }

    public static Integer[] getCartIds() {
        return cartIds.toArray(new Integer[0]);
    }

    public static void clear() {
        userIds.clear();
        cartIds.clear();
    }
}
