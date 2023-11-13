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
        int year = 2023;
        int month = 12;
        int isSunday = 7;
        int isChristmas = 25;

        LocalDate localDate = LocalDate.of(year, month, date);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        int date_value = dayOfWeek.getValue();

        return date == isChristmas || date_value == isSunday;
    }

    public String getDiscountName() {
        String specialDiscountMessage = "특별 할인";

        return specialDiscountMessage;
    }

}
