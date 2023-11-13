package christmas.strategy;

import christmas.domain.Menu;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;
import java.util.stream.IntStream;

public abstract class WeekDiscount implements DiscountStrategy {
    protected final int[] applicableDays;
    protected final String menuType;
    protected final int discountAmount;

    public WeekDiscount(int[] applicableDays, String menuType, int discountAmount) {
        this.applicableDays = applicableDays;
        this.menuType = menuType;
        this.discountAmount = discountAmount;
    }

    @Override
    public int applyDiscount(Map<String, Integer> menus, int date) {
        return menus.entrySet().stream()
                .filter(entry -> Menu.getMenuType(entry.getKey()).equals(menuType))
                .mapToInt(entry -> discountAmount * entry.getValue())
                .sum();
    }

    public boolean isApplicable(int date) {
        LocalDate localDate = LocalDate.of(2023, 12, date);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        int date_value = dayOfWeek.getValue();

        return IntStream.of(applicableDays).anyMatch(day -> day == date_value);
    }
}

