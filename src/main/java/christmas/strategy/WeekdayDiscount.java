package christmas.strategy;

public class WeekdayDiscount extends WeekDiscount {
    public WeekdayDiscount() {
        super(new int[]{1, 2, 3, 4, 7}, "dessert", 2023);
    }

    public String getDiscountName() {

        return "평일 할인";
    }
}
