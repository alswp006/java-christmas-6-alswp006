package christmas.controller;

import christmas.View.InputView;
import christmas.View.OutputView;
import christmas.domain.Benefit;
import christmas.strategy.Discount;
import christmas.domain.Menu;

import java.util.List;
import java.util.Map;

public class Controller {
    OutputView outputView = new OutputView();
    Map<String, Integer> orderMenu;
    int date;

    public void inputProcess() {
        InputView input = new InputView();

        date = input.readDate();
        orderMenu = input.readMenu(Menu.getMenuNames(), Menu.getDrinkMenuNames());

        outputView.eventMessage(date);
        outputView.printMenu(orderMenu);
    }

    public void outputProcess() {
        Discount discount = new Discount();
        Benefit benefit = new Benefit();

        int totalPrice = Menu.totalPrice(orderMenu);
        int totalDiscount = discount.totalDiscount(orderMenu, date);
        int benefitTotalPrice = totalDiscount + benefit.benefitPrice(totalPrice);
        List<String> discountDetails = discount.discountDetails(orderMenu, date, totalPrice);

        outputView.printTotalPrice(totalPrice);
        outputView.printBenefit(totalPrice);
        outputView.printDiscountDetails(discountDetails);
        outputView.printTotalBenefitPrice(benefitTotalPrice);
        outputView.printApplyDiscountPrice(totalPrice - totalDiscount);
        outputView.printEventBadge(benefit.eventbadge(benefitTotalPrice));
    }
}

