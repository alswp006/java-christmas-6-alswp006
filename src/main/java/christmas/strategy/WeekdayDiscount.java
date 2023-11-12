package christmas.strategy;

import christmas.domain.Menu;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;
import java.util.stream.IntStream;

public class WeekdayDiscount implements DiscountStrategy {
    private static final int[] WEEKDAY_DAYS = {1, 2, 3, 4, 7};
    private static final int DISCOUNT_PER_ITEM = 2023;

    @Override
    public int applyDiscount(Map<String, Integer> menus, int date) {
        if (!isApplicable(date)) return 0;
        return menus.entrySet().stream()
                .filter(entry -> Menu.getMenuType(entry.getKey()).equals("dessert"))
                .mapToInt(entry -> DISCOUNT_PER_ITEM * entry.getValue())
                .sum();
    }

    public boolean isApplicable(int date) {
        LocalDate localDate = LocalDate.of(2023, 12, date);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        int date_value = dayOfWeek.getValue();
        return IntStream.of(WEEKDAY_DAYS).anyMatch(day -> day == date_value);
    }

    public String getDiscountName() {
        return "평일 할인";
    }
}
