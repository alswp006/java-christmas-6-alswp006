package christmas.View;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InputValidation {
    private static final String ERROR_DATE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final String ERROR_MENU = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    public static void inputDateCheck(String input) {
        int date = inputDateTypeCheck(input);

        inputDataNumberCheck(date);
    }

    public static void inputMenuCheck(String input, List<String> menu) {
        String[] items = input.split(",");

        inputFormatCheck(input);

        Arrays.stream(items).forEach(item -> {
            inputMenuMatchCheck(item, menu);
            inputMenuNumberCheck(item);
        });
    }

    public static void orderValidationCheck(Map<String, Integer> menus, List<String> drinks) {
        orderMenuAllDrink(menus, drinks);
        menuQuantityExceed(menus);
    }

    private static void orderMenuAllDrink(Map<String, Integer> menus, List<String> drinks) {
        int count = (int) drinks.stream()
                .filter(menus::containsKey)
                .count();

        if (count == menus.size()) {
            System.out.println("음료만 주문할 수 없습니다!\n다시 입력해주세요.");
            throw new IllegalArgumentException();
        }
    }

    private static void menuQuantityExceed(Map<String, Integer> menus) {
        int menuQuantity = menus.values().stream().mapToInt(Integer::intValue).sum();

        if (menuQuantity > 20) {
            System.out.println("메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다!\n다시 입력해주세요.");
            throw new IllegalArgumentException();
        }
    }

    private static void inputFormatCheck(String input) {
        long dashCount = input.chars().filter(ch -> ch == '-').count();
        long commaCount = input.chars().filter(ch -> ch == ',').count();

        if (dashCount - 1 != commaCount) {
            System.out.println(ERROR_MENU);
            throw new IllegalArgumentException();
        }
    }

    private static void inputMenuMatchCheck(String item, List<String> menu) {
        String[] parts = item.split("-");
        if (parts.length == 0 || !menu.contains(parts[0])) {
            System.out.println(ERROR_MENU);
            throw new IllegalArgumentException();
        }
    }

    private static void inputMenuNumberCheck(String item) {
        try {
            int num = inputMenuNumberTypeCheck(item.split("-")[1]);

            if (num < 1) {
                System.out.println(ERROR_MENU);
                throw new IllegalArgumentException();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(ERROR_MENU);
            throw new IllegalArgumentException();
        }

    }

    private static int inputMenuNumberTypeCheck(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println(ERROR_MENU);
            throw new IllegalArgumentException();
        }
    }

    private static int inputDateTypeCheck(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println(ERROR_DATE);
            throw new IllegalArgumentException();
        }
    }

    private static void inputDataNumberCheck(int date) {
        if (date < 1 || date > 31) {
            System.out.println(ERROR_DATE);
            throw new IllegalArgumentException();
        }
    }
}
