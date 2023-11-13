package christmas.domain;

public class Benefit {

    public int benefitPrice(int totalPrice) {
        int benefit = 0;
        int applyBenefitPrice = 120000;
        int benefitPrice = 25000;

        if (totalPrice >= applyBenefitPrice) {
            benefit = benefitPrice;
        }

        return benefit;
    }

    public String eventbadge(int totalBenefitPrice) {
        int santa = 20000;
        int tree = 10000;
        int star = 5000;

        if (totalBenefitPrice >= santa) {
            return "산타";
        }
        if (totalBenefitPrice >= tree) {
            return "트리";
        }
        if (totalBenefitPrice >= star) {
            return "별";
        }
        return "없음";
    }
}
