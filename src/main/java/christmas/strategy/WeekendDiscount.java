package christmas.strategy;

public class WeekendDiscount extends WeekDiscount {
    public WeekendDiscount() {
        super(new int[]{5, 6}, "main", 2023);
    }

    @Override
    public String getDiscountName() {
        String weekendDiscountName = "주말 할인";

        return weekendDiscountName;
    }
}
