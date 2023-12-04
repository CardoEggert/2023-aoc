package advent.of.code;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ScratchCardLotoTest {

    @Test
    void getTotalScratchCards() {
        final ScratchCardLoto loto = new ScratchCardLoto(
                List.of(
                        new ScratchCard(1, List.of(41, 48, 83, 86, 17), List.of(83, 86, 6, 31, 17, 9, 48, 53)),
                        new ScratchCard(2, List.of(13, 32, 20, 16, 61), List.of(61, 30, 68, 82, 17, 32, 24, 19)),
                        new ScratchCard(3, List.of(1, 21, 53, 59, 44), List.of(69, 82, 63, 72, 16, 21, 14, 1)),
                        new ScratchCard(4, List.of(41, 92, 73, 84, 69), List.of(59, 84, 76, 51, 58, 5, 54, 83)),
                        new ScratchCard(5, List.of(87, 83, 26, 28, 32), List.of(88, 30, 70, 12, 93, 22, 82, 36)),
                        new ScratchCard(6, List.of(31, 18, 13, 56, 72), List.of(74, 77, 10, 23, 35, 67, 36, 11))));
        Assertions.assertThat(loto.getTotalScratchCardsCount()).isEqualTo(30);
    }
}
