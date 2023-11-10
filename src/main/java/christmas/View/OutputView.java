package christmas.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OutputView {
    private final String orderMenu = "<주문 메뉴>";
    private final String noDiscountPrice = "<할인 전 총주문 금액>";
    private final String freeGift = "<증정 메뉴>";
    private final String discountDetails = "<혜택 내역>";
    private final String discountPrice = "<총 혜택 금액>";
    private final String applyDiscountPrice = "<할인 후 예상 결제 금액>";
    private final String eventBadge = "<12월 이벤트 배지>";

    public void eventMessage(int date){
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n", date);
    }
    public void printMenu(Map<String, Integer> orderMenus) {
        System.out.println();
        System.out.println(orderMenu);
        orderMenus.
                forEach((key, value) -> System.out.println(key + " " + value + "개"));

    }

    public void printTotalPrice(int totalPrice){
        System.out.println();
        System.out.println(noDiscountPrice);
        System.out.printf("%,d\n",totalPrice);
    }
}
