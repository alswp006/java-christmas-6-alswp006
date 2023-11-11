package christmas;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

public class UnitTest extends NsTest {
    @DisplayName("날짜 입력에 대한 출력 확인")
    @Test
    void inputDateTest() {
        assertSimpleTest(() -> {
            run("32", "a", "10", "해산물파스타-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @DisplayName(" 입력에 대한 출력 확인")
    @Test
    void inputMenuTest() {
        assertSimpleTest(() -> {
            run("10",
                    "해산물파스타 -1",
                    "해산물파스타- 1",
                    "해산물파스타",
                    "해산물파슷아-1",
                    "1",
                    "-",
                    "해산물파슷아-ㅁ",
                    "해산물파스타-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @DisplayName("이벤트 메시지 출력 확인")
    @Test
    void outputEventMessageTest() {
        assertSimpleTest(() -> {
            run("10", "해산물파스타-1,레드와인-2");
            assertThat(output()).contains("12월 10일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        });
    }

    @DisplayName("메뉴 출력 확인")
    @Test
    void outputMenuTest() {
        assertSimpleTest(() -> {
            run("10", "해산물파스타-1,레드와인-2");
            assertThat(output()).contains("해산물파스타 1개", "레드와인 2개");
        });
    }

    @DisplayName("할인 적용 확인")
    @Test
    void discountTest() {
        assertSimpleTest(() -> {
            run("26", "타파스-1,제로콜라-1");
            assertThat(output()).contains("없음");
        });
    }

    @DisplayName("할인 적용 및 할인 후 예상 결제 금액 확인")
    @Test
    void discountTest2() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                    "크리스마스 디데이 할인: -1,200원",
                    "평일 할인: -4,046원",
                    "특별 할인: -1,000원",
                    "135,754원");
        });
    }

    @DisplayName("샴페인 증정 확인")
    @Test
    void benefitTest() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                    "샴페인 1개",
                    "증정 이벤트: -25,000원");
        });
    }

    @DisplayName("총 혜택 금액 확인")
    @Test
    void totalBenefitPriceTest() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains("-31,246원");
        });
    }

    @DisplayName("이벤트 배지 확인")
    @Test
    void eventBadgeTest() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains("산타");
        });
    }

    @DisplayName("음료만 주문시 안내 메시지 출력")
    @Test
    void orderDrink(){
        assertSimpleTest(() -> {
            run("3", "제로콜라-3", "티본스테이크-2");
            assertThat(output()).contains("음료만 주문할 수 없습니다!");
        });
    }

    @DisplayName("메뉴 20개 초과 시 안내메시지 출력")
    @Test
    void menuQuantityExceedTest(){
        assertSimpleTest(() -> {
            run("3", "시저샐러드-10,티본스테이크-11", "티본스테이크-2");
            assertThat(output()).contains("메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다!");
        });
    }



    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
