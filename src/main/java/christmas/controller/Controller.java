package christmas.controller;

import christmas.View.InputView;
import christmas.View.OutputView;
import christmas.domain.Benefit;
import christmas.strategy.Discount;
import christmas.strategy.DiscountStrategy;
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

    public void play(){
        inputProcess();
        outputProcess();
    }


    private void inputProcess() {
        date = input.readDate();
        orderMenu = input.readMenu(Menu.getMenuNames(), Menu.getDrinkMenuNames());

        outputView.eventMessage(date);
        outputView.printMenu(orderMenu);
    }

    private void outputProcess(){
        int totalPrice = Menu.totalPrice(orderMenu);

        outputView.printTotalPrice(totalPrice);
        outputView.printBenefit(totalPrice);

        discount.champagneFree(totalPrice);

        int totalDiscount = discountProcess(totalPrice);
        outputView.printDiscountDetails(discount.getDiscountDetails());
        int benefitTotalPrice = totalDiscount + benefit.benefitPrice(totalPrice);
        outputView.printTotalBenefitPrice(benefitTotalPrice);
        outputView.printApplyDiscountPrice(totalPrice - totalDiscount);
        outputView.printEventBadge(benefit.eventbadge(benefitTotalPrice));
    }

    private int discountProcess(int totalPrice){
        int minimumPrice = 10000;

        if (totalPrice < minimumPrice) {
            return 0;
        }

        List<DiscountStrategy> applicableStrategies = discount.getApplicableStrategies(date);

        return applicableStrategies.stream()
                .mapToInt(strategy -> {
                    discount.setDiscountStrategy(strategy);
                    return discount.applyDiscounts(orderMenu, date);
                })
                .sum();
    }
}

