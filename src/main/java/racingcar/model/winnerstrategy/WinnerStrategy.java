package racingcar.model.winnerstrategy;

import java.util.List;
import racingcar.model.Cars;

public interface WinnerStrategy {
    List<String> judgeWinner(Cars cars);
}
