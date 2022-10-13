package com.gbtec.gameoflife.implementation.MaxVersion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RuleCheckTest {

    @Test
    void checkRules_should_check_for_rule_stay_alive_3_neighbors() {
        // Given
        int[][] grid = {
                {0, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        // When
        int arrayValue = RuleCheck.checkRules(grid, 1, 1, 3);

        // Then
        Assertions.assertEquals(1,arrayValue);
    }

    @Test
    void checkRules_should_check_for_rule_stay_alive_2_neighbors() {
        // Given
        int[][] grid = {
                {0, 1, 0, 0},
                {1, 1, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        // When
        int arrayValue = RuleCheck.checkRules(grid, 1, 1, 2);

        // Then
        Assertions.assertEquals(1,arrayValue);
    }

    @Test
    void checkRules_should_check_for_rule_dies_4_or_more_neighbors() {
        // Given
        int[][] grid = {
                {0, 1, 0, 0},
                {1, 1, 1, 0},
                {1, 1, 0, 0},
                {0, 0, 0, 0}
        };

        // When
        int arrayValue = RuleCheck.checkRules(grid, 2, 1, 4);

        // Then
        Assertions.assertEquals(0,arrayValue);
    }

    @Test
    void checkRules_should_check_for_rule_dies_1_or_less_neighbors() {
        // Given
        int[][] grid = {
                {0, 1, 0, 0},
                {1, 1, 1, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        // When
        int arrayValue = RuleCheck.checkRules(grid, 1, 3, 1);

        // Then
        Assertions.assertEquals(0,arrayValue);
    }

    @Test
    void checkRules_should_check_for_rule_stays_dead() {
        // Given
        int[][] grid = {
                {0, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        // When
        int arrayValue = RuleCheck.checkRules(grid, 3, 3, 0);

        // Then
        Assertions.assertEquals(0,arrayValue);
    }

    @Test
    void checkRules_should_check_for_rule_born() {
        // Given
        int[][] grid = {
                {0, 1, 0, 0},
                {1, 1, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        // When
        int arrayValue = RuleCheck.checkRules(grid, 0, 0, 3);

        // Then
        Assertions.assertEquals(1,arrayValue);
    }
}