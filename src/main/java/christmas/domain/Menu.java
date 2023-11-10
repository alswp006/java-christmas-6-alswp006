package christmas.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum Menu {
    APPETIZER1("양송이수프", 6000, "appetizer"),
    APPETIZER2("타파스", 5500, "appetizer"),
    APPETIZER3("시저샐러드", 8000, "appetizer"),
    MAIN1("티본스테이크", 55000, "main"),
    MAIN2("바비큐립", 54000, "main"),
    MAIN3("해산물파스타", 35000, "main"),
    MAIN4("크리스마스파스타", 25000, "main"),
    DESSERT1("초코케이크", 15000, "dessert"),
    DESSERT2("아이스크림", 5000, "dessert"),
    DRINK1("제로콜라", 3000, "drink"),
    DRINK2("레드와인", 60000, "drink"),
    DRINK3("샴페인", 25000, "drink");

    private final String menuName;
    private final int price;
    private final String menuType;

    Menu(String menuName, int price, String menuType) {
        this.menuName = menuName;
        this.price = price;
        this.menuType = menuType;
    }

    public String getMenuName() {
        return this.menuName;
    }

    public int getPrice() {
        return this.price;
    }

    public String getType() {
        return this.menuType;
    }

    public static List<String> getMenuNames() {
        return Arrays.stream(Menu.values())
                .map(Menu::getMenuName)
                .collect(Collectors.toList());
    }

    public static int totalPrice(Map<String, Integer> order) {
        return order.entrySet().stream()
                .mapToInt(menu -> getMenuPrice(menu.getKey(), menu.getValue()))
                .sum();
    }

    static Menu findMenuByName(String menuName) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.getMenuName().equals(menuName))
                .findFirst()
                .orElse(null);
    }
    public static String getMenuType(String menuName) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.getMenuName().equals(menuName))
                .findFirst()
                .map(Menu::getType)
                .orElse(null);
    }

    private static int getMenuPrice(String menuName, int quantity) {
        Menu menu = findMenuByName(menuName);

        return menu.getPrice() * quantity;
    }

}
