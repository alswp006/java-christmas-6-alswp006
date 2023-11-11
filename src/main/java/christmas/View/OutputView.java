package christmas.View;

import java.util.Map;

public class OutputView {
    private final String ORDERMENU = "<주문 메뉴>";
    private final String TOTALPRICE = "<할인 전 총주문 금액>";
    private final String freeGift = "<증정 메뉴>";
    private final String DISCOUNTDETAILS = "<혜택 내역>";
    private final String discountPrice = "<총 혜택 금액>";
    private final String applyDiscountPrice = "<할인 후 예상 결제 금액>";
    private final String eventBadge = "<12월 이벤트 배지>";

    public void eventMessage(int date){
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n", date);
        System.out.println();
    }
    public void printMenu(Map<String, Integer> orderMenus) {
        System.out.println(ORDERMENU);
        orderMenus.
                forEach((key, value) -> System.out.println(key + " " + value + "개"));
        System.out.println();
    }

    public void printTotalPrice(int totalPrice) {
        System.out.println(TOTALPRICE);
        System.out.printf("%,d원\n", totalPrice);
        System.out.println();
    }

    public void printBenefit(String champagne){
        System.out.println(freeGift);
        System.out.println(champagne);
        System.out.println();
    }

    public void printDiscountDetails(String discountDetails){
        System.out.println(DISCOUNTDETAILS);
        System.out.println(discountDetails);
        System.out.println();
    }

    public void printApplyDiscountPrice(int price){
        System.out.println(applyDiscountPrice);
        System.out.printf("%,d원\n",price);
    }
}
