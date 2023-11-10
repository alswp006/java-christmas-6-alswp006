package christmas.controller;

import christmas.View.InputView;
import christmas.View.OutputView;
import christmas.domain.Menu;

import java.util.Map;

public class Controller {
    InputView input = new InputView();
    OutputView outputView = new OutputView();
    public void inputProcess(){
        int date = input.readDate();
        Map<String, Integer> orderMenu = input.readMenu(Menu.getMenuNames());

        outputView.eventMessage(date);
        outputView.printMenu(orderMenu);
    }
}
