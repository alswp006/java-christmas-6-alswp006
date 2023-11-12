package christmas.strategy;

import christmas.strategy.DiscountStrategy;

import java.util.Map;

public class ChristmasDDAYDiscount implements DiscountStrategy {
    private static final int DISCOUNT_START = 1000;
    private static final int DAILY_INCREASE = 100;

    @Override
    public int applyDiscount(Map<String, Integer> menus, int date) {
        return DISCOUNT_START + (date - 1) * DAILY_INCREASE;
    }

    @Override
    public boolean isApplicable(int date) {
        return date <= 25;
    }

    public String getDiscountName() {
        return "크리스마스 디데이 할인";
    }
}
