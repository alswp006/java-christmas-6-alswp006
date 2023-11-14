package christmas.strategy;

import java.util.Map;

public interface DiscountStrategy {
    int applyDiscount(Map<String, Integer> menus, int date);

    boolean isApplicable(int date);

    String getDiscountName();
}
