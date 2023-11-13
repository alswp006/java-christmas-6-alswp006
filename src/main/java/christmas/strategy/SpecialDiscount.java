package christmas.strategy;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;

public class SpecialDiscount implements DiscountStrategy {
    private static final int DISCOUNT = 1000;

    @Override
    public int applyDiscount(Map<String, Integer> menus, int date) {
        return DISCOUNT;
    }

    public boolean isApplicable(int date) {
        LocalDate localDate = LocalDate.of(2023, 12, date);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        int date_value = dayOfWeek.getValue();

        return date_value == 25 || date_value == 7;
    }

    public String getDiscountName() {
        return "특별 할인";
    }

}
