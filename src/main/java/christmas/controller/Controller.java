package christmas.controller;

import christmas.View.InputView;
import christmas.domain.Menu;

public class Controller {
    InputView input = new InputView();
    public void inputProcess(){
        input.readDate();
        input.readMenu(Menu.getMenuNames());
    }
}
