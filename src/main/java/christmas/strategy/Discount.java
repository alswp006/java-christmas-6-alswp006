package christmas.strategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Discount {
    private DiscountStrategy discountStrategy;
    private final List<String> discountDetails = new ArrayList<>();

    private static final List<DiscountStrategy> strategies = Arrays.asList(
            new WeekdayDiscount(),
            new WeekendDiscount(),
            new ChristmasDDAYDiscount(),
            new SpecialDiscount()
    );

    public void setDiscountStrategy(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public int applyDiscounts(Map<String, Integer> menus, int date) {
        if (discountStrategy != null) {
            int discountAmount = discountStrategy.applyDiscount(menus, date);
            String discountAmountStr = String.format(": -%,d원",discountAmount);
            discountDetails.add(discountStrategy.getDiscountName() + discountAmountStr);
            return discountAmount;
        }
        return 0;
    }

    public List<String> getDiscountDetails() {
        return discountDetails;
    }

    public void champagneFree(int totalPrice) {
        if (totalPrice >= 120000) {
            discountDetails.add("증정 이벤트: -25,000원");
        }
    }

    public List<DiscountStrategy> getApplicableStrategies(int date) {
        return strategies.stream()
                .filter(strategy -> strategy.isApplicable(date))
                .collect(Collectors.toList());
    }
}

