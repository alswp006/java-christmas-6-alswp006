package christmas.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Discount {
    List<String> discounts;
    final int[] WEEKENDVALUE = {5, 6};
    private final int DISCOUNT = 1000;

    public Discount() {
        this.discounts = new ArrayList<>();
    }

    private int getWeekValue(int date) {
        LocalDate localDate = LocalDate.of(2023, 12, date);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();

        return dayOfWeek.getValue();
    }

    public int applyDiscounts(Map<String, Integer> menus, int date) {
        int weekValue = getWeekValue(date);
        String discountMenuType = getMenuType(weekValue);
        int totalDiscount = weekDiscountPrice(menus, discountMenuType);
        totalDiscount += specialDiscount(date, weekValue) + chrismasDiscount(date);

        return totalDiscount;
    }

    private int weekDiscountPrice(Map<String, Integer> menus, String discountMenuType) {
        return menus.entrySet().stream()
                .filter(menu -> discountMenuType.equals(Menu.getMenuType(menu.getKey())))
                .mapToInt(menu -> weekDiscount(Menu.getMenuType(menu.getKey()), menu.getValue()))
                .sum();
    }

    private int weekDiscount(String menuType, int quantity) {
        int discount = 2023 * quantity;

        if (menuType.equals("dessert")) {
            discounts.add(String.format("평일 할인: -%,d원", discount));
        }
        if (menuType.equals("main")) {
            discounts.add(String.format("주말 할인: -%,d원", discount));
        }

        return discount;
    }

    public void champagneFree(String champagne) {
        if (!champagne.equals("없음")) {
            discounts.add("증정 이벤트: -25,000원");
        }
    }

    private int specialDiscount(int date, int weekValue) {
        if (date == 25 || weekValue == 7) {
            discounts.add(String.format("특별 할인: -%,d원", DISCOUNT));
            return DISCOUNT;
        }
        return 0;
    }

    private int chrismasDiscount(int date) {
        int discountPrice = 0;

        if (date <= 25) {
            int DAILY_INCREASE = 100;

            discountPrice = DISCOUNT + (date - 1) * DAILY_INCREASE;
        }
        if (discountPrice != 0) {
            discounts.add(String.format("크리스마스 디데이 할인: -%,d원", discountPrice));
            return discountPrice;
        }

        return 0;
    }

    private String getMenuType(int weekValue) {
        String discountMenuType = "dessert";

        if (IntStream.of(WEEKENDVALUE).anyMatch(day -> day == weekValue)) {
            discountMenuType = "main";
        }

        return discountMenuType;
    }


    public String getDiscountDetails() {
        if (discounts.isEmpty()) {
            return "없음";
        }
        return String.join("\n", discounts);
    }

}
