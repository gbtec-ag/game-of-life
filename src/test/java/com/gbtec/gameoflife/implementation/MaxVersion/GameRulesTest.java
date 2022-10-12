package com.gbtec.gameoflife.implementation.MaxVersion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GameRulesTest {

    @Test
    void testAddition_should_sum_x_and_y() {
        // given
        int x = 1;
        int y = 8;

        // when
        int result = GameRules.testAddition(x, y);


        // then
        Assertions.assertEquals(x + y, result);

    }
}