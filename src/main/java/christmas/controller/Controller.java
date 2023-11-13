package christmas.controller;

import christmas.View.InputView;
import christmas.View.OutputView;
import christmas.domain.Benefit;
import christmas.strategy.Discount;
import christmas.domain.Menu;

import java.util.List;
import java.util.Map;

public class Controller {
    InputView input = new InputView();
    OutputView outputView = new OutputView();
    Discount discount = new Discount();
    Benefit benefit = new Benefit();
    Map<String, Integer> orderMenu;
    int date;

    public void inputProcess() {
        date = input.readDate();
        orderMenu = input.readMenu(Menu.getMenuNames(), Menu.getDrinkMenuNames());

        outputView.eventMessage(date);
        outputView.printMenu(orderMenu);
    }

    public void outputProcess(){
        int totalPrice = Menu.totalPrice(orderMenu);
        outputView.printTotalPrice(totalPrice);
        outputView.printBenefit(totalPrice);

        int totalDiscount = discount.totalDiscount(orderMenu, date);
        List<String> discountDetails = discount.discountDetails(orderMenu, date, totalPrice);
        outputView.printDiscountDetails(discountDetails);

        int benefitTotalPrice = totalDiscount + benefit.benefitPrice(totalPrice);
        outputView.printTotalBenefitPrice(benefitTotalPrice);
        outputView.printApplyDiscountPrice(totalPrice - totalDiscount);
        outputView.printEventBadge(benefit.eventbadge(benefitTotalPrice));
    }
}

