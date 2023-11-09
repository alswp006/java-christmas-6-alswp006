package christmas.View;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputValidation {
    private static final String ERROR_DATE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final String ERROR_MENU = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    public static void inputDateCheck(String input){
        inputDateTypeCheck(input);
        inputDataNumberCheck(input);
    }

    public static void inputMenuCheck(String input, List<String> menu){
        inputFormatCheck(input);
        inputMenuNumberCheck(input);
        inputMenuMatchCheck(input, menu);
    }

    private static void inputFormatCheck(String input){
        int dashCount = (int) input.chars().filter(dash -> dash == '-').count();
        int commaCount = (int) input.chars().filter(comma -> comma == ',').count();

        if (dashCount-1 != commaCount){
            System.out.println(ERROR_MENU);
            throw new IllegalArgumentException();
        }
    }
    private static void inputMenuMatchCheck(String input, List<String> menu){
        Arrays.stream(input.split(","))
                .map(item -> item.split("-"))
                .forEach(item -> {
                    System.out.println(Arrays.toString(item));
                    if (!menu.contains(item[0])) {
                        System.out.println(ERROR_MENU);
                        throw new IllegalArgumentException();
                    }
                });
    }

    private static void inputMenuNumberCheck(String input){
        Arrays.stream(input.split(","))
                .map(item -> item.split("-")[1])
                .forEach(items -> {
                    try {
                        int num = Integer.parseInt(items);
                        if (num < 1) {
                            System.out.println(ERROR_MENU);
                            throw new IllegalArgumentException();
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(ERROR_MENU);
                        throw new IllegalArgumentException();
                    }
                });
    }
    private static void inputDateTypeCheck(String input){
        try {
            Integer.parseInt(input);
        }catch (NumberFormatException e){
            System.out.println(ERROR_DATE);
            throw new IllegalArgumentException();
        }
    }

    private static void inputDataNumberCheck(String input){
        int date = Integer.parseInt(input);

        if (date < 1 || date > 31){
            System.out.println(ERROR_DATE);
            throw new IllegalArgumentException();
        }
    }
}
