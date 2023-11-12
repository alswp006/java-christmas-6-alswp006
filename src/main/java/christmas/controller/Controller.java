package christmas.controller;

import christmas.View.InputView;
import christmas.View.OutputView;
import christmas.domain.Benefit;
import christmas.domain.Discount;
import christmas.domain.DiscountStrategy;
import christmas.domain.Menu;

import java.util.List;
import java.util.Map;

public class Controller {
    InputView input = new InputView();
    OutputView outputView = new OutputView();
    Discount discount = new Discount();
    Benefit benefit = new Benefit();

    public void inputProcess() {
        int date = input.readDate();
        Map<String, Integer> orderMenu = input.readMenu(Menu.getMenuNames(), Menu.getDrinkMenuNames());

        outputView.eventMessage(date);
        outputView.printMenu(orderMenu);

        int totalPrice = Menu.totalPrice(orderMenu);
        int applyDiscountPrice = totalPrice;

        outputView.printTotalPrice(totalPrice);
        outputView.printBenefit(totalPrice);
        if (totalPrice < 10000) {
            outputView.printUnappliedDiscount();
            return;
        }
        discount.champagneFree(totalPrice);

        List<DiscountStrategy> applicableStrategies = discount.getApplicableStrategies(date);
        int totalDiscount = 0;
        for (DiscountStrategy strategy : applicableStrategies) {
            discount.setDiscountStrategy(strategy);
            totalDiscount += discount.applyDiscounts(orderMenu, date);
        }

        if (totalDiscount == 0) {
            System.out.println("없음");
            return;
        }

        List<String> discountDetails = discount.getDiscountDetails();
        if (discountDetails.isEmpty()) {
            System.out.println("없음");
        } else {
            outputView.printDiscountDetails(discountDetails);
        }

        int benefitTotalPrice = totalDiscount + benefit.benefitPrice(totalPrice);
        outputView.printTotalBenefitPrice(benefitTotalPrice);

        applyDiscountPrice -= totalDiscount;
        outputView.printApplyDiscountPrice(applyDiscountPrice);
        outputView.printEventBadge(benefit.eventbadge(benefitTotalPrice));
    }
}

