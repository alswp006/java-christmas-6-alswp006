package christmas.controller;

import christmas.View.InputView;
import christmas.View.OutputView;
import christmas.domain.Benefit;
import christmas.domain.Discount;
import christmas.domain.Menu;

import java.util.Map;

public class Controller {
    InputView input = new InputView();
    OutputView outputView = new OutputView();
    Discount discount = new Discount();
    Benefit benefit = new Benefit();

    public void inputProcess(){
        int date = input.readDate();
        Map<String, Integer> orderMenu = input.readMenu(Menu.getMenuNames());

        outputView.eventMessage(date);
        outputView.printMenu(orderMenu);
        int totalPrice = Menu.totalPrice(orderMenu);
        outputView.printTotalPrice(totalPrice);
        String champagne = benefit.champagneFree(totalPrice);
        outputView.printBenefit(champagne);
        discount.champagneFree(champagne);
        int totalDiscount = discount.applyDiscounts(orderMenu, date);
        String discountDetails = discount.getDiscountDetails();
        outputView.printDiscountDetails(discountDetails);
        outputView.printApplyDiscountPrice(totalPrice - totalDiscount);
    }
}
