package christmas.View;

import java.util.List;
import java.util.Map;

public class OutputView {

    public void eventMessage(int date) {
        String EVENTMESSAGE = String.format("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n", date);

        System.out.printf(EVENTMESSAGE);
        System.out.println();
    }

    public void printMenu(Map<String, Integer> orderMenus) {
        String ORDERMENU = "<주문 메뉴>";

        System.out.println(ORDERMENU);
        orderMenus.
                forEach((key, value) -> System.out.println(key + " " + value + "개"));
        System.out.println();
    }

    public void printTotalPrice(int totalPrice) {
        String TOTALPRICE = "<할인 전 총주문 금액>";

        System.out.println(TOTALPRICE);
        System.out.printf("%,d원\n", totalPrice);
        System.out.println();
    }

    public void printBenefit(int totalPrice) {
        String freeGift = "<증정 메뉴>";
        int champagne = totalPrice/120000;

        System.out.println(freeGift);

        if (champagne > 0) {
            System.out.println("샴페인 1개\n");
            return;
        }

        System.out.println("없음\n");
    }

    public void printDiscountDetails(List<String> discountDetails) {
        String DISCOUNTDETAILS = "<혜택 내역>";

        System.out.println(DISCOUNTDETAILS);
        if (discountDetails.isEmpty()){
            System.out.println("없음\n");
            return;
        }
        discountDetails.forEach(System.out::println);
        System.out.println();
    }


    public void printApplyDiscountPrice(int price) {
        String applyDiscountPrice = "<할인 후 예상 결제 금액>";

        System.out.println(applyDiscountPrice);
        System.out.printf("%,d원\n", price);
        System.out.println();
    }

    public void printTotalBenefitPrice(int price){
        String totalBenefitPriceMessage = "<총혜택 금액>";
        String totalBenefitPrice = String.format("-%,d원\n", price);

        System.out.println(totalBenefitPriceMessage);

        if (price == 0){
            totalBenefitPrice = totalBenefitPrice.substring(1);
        }

        System.out.println(totalBenefitPrice);
    }

    public void printEventBadge(String badge){
        String eventBadge = "<12월 이벤트 배지>";

        System.out.println(eventBadge);
        System.out.println(badge);
    }

    public void printUnappliedDiscount(){
        String unapliedDiscountMessage = "총주문 금액 10,000원 이상부터 이벤트가 적용됩니다.";

        System.out.println(unapliedDiscountMessage);

    }
}
