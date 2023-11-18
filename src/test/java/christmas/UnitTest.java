package christmas;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitTest extends NsTest {

    @DisplayName("할인 적용 확인")
    @Test
    void discountTest() {
        assertSimpleTest(() ->{
            run("10", "타파스-1,제로콜라-2");
            assertThat(output()).contains(
                    "크리스마스 디데이 할인: -1,900원\n",
                    "특별 할인: -1,000원",
                    "-2,900원");
        });

        int count = (int) Arrays.stream(output().split("\n"))
                .filter(s -> s.contains("없음"))
                .count();
        assertEquals(2, count);
    }

    @DisplayName("할인 미적용 확인")
    @Test
    void notAppliedDiscountTest() {
        assertSimpleTest(() ->{
            run("10", "타파스-1,제로콜라-1");
            assertThat(output()).contains("0원");
        });

        int count = (int) Arrays.stream(output().split("\n"))
                .filter(s -> s.contains("없음"))
                .count();
        assertEquals(3, count);
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

    @DisplayName("총 금액이 10000원 미만일 시 이벤트 적용 x")
    @Test
    void totalPriceTest(){
        assertSimpleTest(() ->{
            run("3", "시저샐러드-1");
            assertThat(output()).contains(
                    "0원",
                    "총주문 금액 10,000원 이상부터 이벤트가 적용됩니다!");
        });

    int count = (int) Arrays.stream(output().split("\n"))
            .filter(s -> s.contains("없음"))
            .count();
    assertEquals(3, count);
    }

    @DisplayName("평일 주말 할인 확인")
    @ParameterizedTest
    @CsvSource({"4,초코케이크-2,평일 할인", "2,티본스테이크-2,주말 할인"})
    void discountTest(String day, String menu, String discountType) {
        assertSimpleTest(() -> {
            run(day, menu);
            assertThat(output()).contains(discountType);
        });
    }

    @DisplayName("이벤트 배지 확인")
    @ParameterizedTest
    @CsvSource({
            "3,티본스테이크-5,산타",
            "3,초코케이크-5,트리",
            "3,초코케이크-3,별"
    })
    void eventBadgeTest(String day, String order, String badge) {
        assertSimpleTest(() -> {
            run(day, order);
            assertThat(output()).contains(badge);
        });
    }

    @DisplayName("크리스마스가 지나고 DDay할인 미출력 확인")
    @Test
    void notDDdayTest() {
        assertSimpleTest(() -> {
            run("26", "초코케이크-3");
            assertThat(output()).doesNotContain("크리스마스 디데이 할인");
        });
    }

    @DisplayName("특별 할인 확인")
    @ParameterizedTest
    @ValueSource(strings = {"10", "17", "24", "25", "31"})
    void SpecialDiscountTest(String day) {
        assertSimpleTest(() -> {
            run(day, "초코케이크-3");
            assertThat(output()).contains("특별 할인");
        });
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
