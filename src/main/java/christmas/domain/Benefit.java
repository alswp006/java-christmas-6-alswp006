package christmas.domain;

public class Benefit {
    public String champagneFree(int totalPrice){

        String champagne = "없음";
        if (totalPrice / 120000 > 1){
            champagne = "샴페인 1개";
        }

        return champagne;
    }
}
