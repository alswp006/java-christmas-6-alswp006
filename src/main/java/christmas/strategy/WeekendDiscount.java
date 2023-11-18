package christmas.strategy;

public class WeekendDiscount extends WeekDiscount {
    public WeekendDiscount() {
        super(new int[]{5, 6}, "main", 2023);
    }

    public String getDiscountName() {

        return "주말 할인";
    }
}
