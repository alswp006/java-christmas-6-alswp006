package christmas.strategy;

import christmas.domain.Menu;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;
import java.util.stream.IntStream;

public class WeekendDiscount implements DiscountStrategy {
    private static final int[] WEEKEND_DAYS = {5, 6};

    private static final int DISCOUNT_MAIN = 2023;

    @Override
    public int applyDiscount(Map<String, Integer> menus, int date) {
        return menus.entrySet().stream()
                .filter(entry -> Menu.getMenuType(entry.getKey()).equals("main"))
                .mapToInt(entry -> DISCOUNT_MAIN * entry.getValue())
                .sum();
    }

    public boolean isApplicable(int date) {
        LocalDate localDate = LocalDate.of(2023, 12, date);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        int date_value = dayOfWeek.getValue();
        return IntStream.of(WEEKEND_DAYS).anyMatch(day -> day == date_value);
    }

    public String getDiscountName() {
        return "주말 할인";
    }
}
