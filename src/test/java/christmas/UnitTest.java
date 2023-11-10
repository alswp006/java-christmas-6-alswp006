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
            run("10", "초코케이크-2");
            assertThat(output()).contains("없음");
        });
    }


    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
