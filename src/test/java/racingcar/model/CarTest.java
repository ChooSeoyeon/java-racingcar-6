package racingcar.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.util.CustomIntGenerator;
import racingcar.util.RandomIntGenerator;

public class CarTest {
    @ParameterizedTest
    @ValueSource(strings = {"pobipo", "po bip"})
    void 글자수_제한_넘긴_이름으로_자동차_생성시_예외_발생한다(String name) {
        assertThatThrownBy(() -> new Car(name, new RandomIntGenerator()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 5자 이하여야 합니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"1,0", "3,0", "5,1", "9,1"}, delimiter = ',')
    void 전진_시도_시_기준점을_넘을때만_위치_1만큼_증가한다(int pickedNumber, int expectedForwardCount) {
        Car car = new Car("test", new CustomIntGenerator(pickedNumber));

        car.tryForward();
        CarState state = car.summarizeCurrentState();

        assertThat(state.forwardCount()).isEqualTo(expectedForwardCount);
    }
}
