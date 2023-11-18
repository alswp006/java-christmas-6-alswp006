package christmas.strategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Discount {
    private final List<String> discountDetails = new ArrayList<>();

    private static final List<DiscountStrategy> strategies = Arrays.asList(
            new WeekdayDiscount(),
            new WeekendDiscount(),
            new ChristmasDDAYDiscount(),
            new SpecialDiscount()
    );

    public List<String> discountDetails(Map<String, Integer> menus, int date, int totalPrice) {
        if (totalPrice >= 10000) {
            applyDiscounts(menus, date);
            applyBenefitEvevnt(totalPrice);
        }

        return discountDetails;
    }

    private void applyDiscounts(Map<String, Integer> menus, int date) {

        for (DiscountStrategy strategy : getApplicableStrategies(date)) {
            int discountAmount = strategy.applyDiscount(menus, date);

            if (discountAmount > 0) {
                String discountAmountStr = String.format(": -%,d원", discountAmount);
                discountDetails.add(strategy.getDiscountName() + discountAmountStr);
            }
        }

    }

    public int totalDiscount(Map<String, Integer> menus, int date, int totalPrice) {
        if (totalPrice < 10000) {
            System.out.println("총주문 금액 10,000원 이상부터 이벤트가 적용됩니다!\n");

            return 0;
        }

        return getApplicableStrategies(date).stream()
                .mapToInt(strategy -> strategy.applyDiscount(menus, date))
                .sum();
    }

    private void applyBenefitEvevnt(int totalPrice) {
        String benefitMessage = "증정 이벤트: -25,000원";
        int applyBenefitPrice = 120000;

        if (totalPrice >= applyBenefitPrice) {
            discountDetails.add(benefitMessage);
        }
    }

    private List<DiscountStrategy> getApplicableStrategies(int date) {
        return strategies.stream()
                .filter(strategy -> strategy.isApplicable(date))
                .collect(Collectors.toList());
    }
}