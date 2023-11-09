package christmas;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
