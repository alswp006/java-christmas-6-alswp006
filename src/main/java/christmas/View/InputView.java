package christmas.View;

import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InputView {
    public int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");

        while (true) {
            try {
                String input = Console.readLine();
                InputValidation.inputDateCheck(input);

                return Integer.parseInt(input);
            } catch (IllegalArgumentException e) {
            }
        }
    }

    public Map<String, Integer> readMenu(List<String> menuNames, List<String> drinks) {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");

        while (true) {
            try {
                String input = Console.readLine();
                InputValidation.inputMenuCheck(input, menuNames);
                Map<String, Integer> menus = getMenu(input);
                InputValidation.menuQuantityExceed(menus);
                InputValidation.orderMenuAllDrink(menus, drinks);

                return menus;
            } catch (IllegalArgumentException e) {
            }
        }
    }

    private Map<String, Integer> getMenu(String input){
        return Arrays.stream(input.split(","))
                .map(item -> item.split("-"))
                .collect(Collectors.toMap(items -> items[0], items -> Integer.parseInt(items[1])));
    }
}
