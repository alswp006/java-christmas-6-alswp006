package christmas.domain;

public class Benefit {
    public String champagneFree(int totalPrice){
        String champagne = "없음";

        if (totalPrice / 120000 > 0){
            champagne = "샴페인 1개";
        }

        return champagne;
    }

    public int benefitPrice(int totalPrice){
        int benefit = 0;

        if (totalPrice >= 120000){
            benefit = 25000;
        }

        return benefit;
    }

    public String eventbadge(int totalBenefitPrice){
        if (totalBenefitPrice >= 20000){
            return "산타";
        }

        if (totalBenefitPrice >= 10000){
            return "트리";
        }

        if (totalBenefitPrice >= 5000){
            return "별";
        }

        return "없음";
    }
}
