package christmas.strategy;

import java.util.Map;

public class ChristmasDDAYDiscount implements DiscountStrategy {
    private static final int DISCOUNT_START = 1000;
    private static final int DAILY_INCREASE = 100;

    public int applyDiscount(Map<String, Integer> menus, int date) {
        return DISCOUNT_START + (date - 1) * DAILY_INCREASE;
    }

    public boolean isApplicable(int date) {
        int christmas = 25;

        return date <= christmas;
    }

    public String getDiscountName() {
        String christmasDDayDiscountMessage = "크리스마스 디데이 할인";

        return christmasDDayDiscountMessage;
    }
}
