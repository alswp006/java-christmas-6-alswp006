package christmas.View;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class InputValidation {
    private static final String ERROR_DATE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final String ERROR_MENU = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    public static void inputDateCheck(String input){
        int date = inputDateTypeCheck(input);
        inputDataNumberCheck(date);
    }

    public static void inputMenuCheck(String input, List<String> menu){
        String[] items = input.split(",");
        inputFormatCheck(input);
        Arrays.stream(items).forEach(item -> {
            inputMenuMatchCheck(item, menu);
            inputMenuNumberCheck(item);
        });
    }

    private static void inputFormatCheck(String input){
        long dashCount = input.chars().filter(ch -> ch == '-').count();
        long commaCount = input.chars().filter(ch -> ch == ',').count();

        if (dashCount - 1 != commaCount){
            System.out.println(ERROR_MENU);
            throw new IllegalArgumentException();
        }
    }
    private static void inputMenuMatchCheck(String item, List<String> menu){
        String[] parts = item.split("-");
        if (parts.length == 0 || !menu.contains(parts[0])) {
            System.out.println(ERROR_MENU);
            throw new IllegalArgumentException();
        }
    }

    private static void inputMenuNumberCheck(String item){
        int num = inputMenuNumberTypeCheck(item.split("-")[1]);
        if (num < 1) {
            System.out.println(ERROR_MENU);
            throw new IllegalArgumentException();
        }
    }
    public static int inputMenuNumberTypeCheck(String input){
        try {
            return Integer.parseInt(input);
        }catch (NumberFormatException e){
            System.out.println(ERROR_MENU);
            throw new IllegalArgumentException();
        }
    }

    private static int inputDateTypeCheck(String input){
        try {
            return Integer.parseInt(input);
        }catch (NumberFormatException e){
            System.out.println(ERROR_DATE);
            throw new IllegalArgumentException();
        }
    }

    private static void inputDataNumberCheck(int date){
        if (date < 1 || date > 31){
            System.out.println(ERROR_DATE);
            throw new IllegalArgumentException();
        }
    }

    public static void orderMenuAllDrink(Map<String, Integer> menus, List<String> drinks){
        for (String drink : drinks){
            menus.remove(drink);
        }

        if (menus.isEmpty()){
            System.out.println("음료만 주문할 수 없습니다!");
            throw new IllegalArgumentException();
        }
    }
}
