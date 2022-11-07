package com.gbtec.gameoflife.implementation.businessLogic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RuleCheckTest {

    @Test
    void checkRules_should_check_for_rule_stay_alive_3_neighbors() {
        // When
        int arrayValue = RuleCheck.checkRules(1, 3);

        // Then
        Assertions.assertEquals(1, arrayValue);
    }

    @Test
    void checkRules_should_check_for_rule_stay_alive_2_neighbors() {
        // When
        int arrayValue = RuleCheck.checkRules(1, 2);

        // Then
        Assertions.assertEquals(1, arrayValue);
    }

    @Test
    void checkRules_should_check_for_rule_dies_4_or_more_neighbors() {
        // When
        int arrayValue = RuleCheck.checkRules(1, 4);

        // Then
        Assertions.assertEquals(0, arrayValue);
    }

    @Test
    void checkRules_should_check_for_rule_dies_1_or_less_neighbors() {
        // When
        int arrayValue = RuleCheck.checkRules(1, 1);

        // Then
        Assertions.assertEquals(0, arrayValue);
    }

    @Test
    void checkRules_should_check_for_rule_stays_dead() {
        // When
        int arrayValue = RuleCheck.checkRules(0, 0);

        // Then
        Assertions.assertEquals(0, arrayValue);
    }

    @Test
    void checkRules_should_check_for_rule_born() {
        // When
        int arrayValue = RuleCheck.checkRules(0, 3);

        // Then
        Assertions.assertEquals(1, arrayValue);
    }

    // ####################################################################
    // ####################################################################
    // ####################################################################
    // ####################################################################

    // Case 1
    @Test
    void countNeighborsForToroidGrid_should_count_neighbors_for_top_left_corner_cell() {
        // Given
        int[][] grid = {
                { 0, 1, 1 },
                { 1, 1, 1 },
                { 1, 1, 1 }
        };

        // When
        int sumNeighbors = RuleCheck.countNeighborsForToroidGrid(grid, 0, 0);

        // Then
        Assertions.assertEquals(8, sumNeighbors);
    }

    // Case 2
    @Test
    void countNeighborsForToroidGrid_should_count_neighbors_for_top_row_cells() {
        // Given
        int[][] grid = {
                { 1, 0, 1 },
                { 1, 1, 1 },
                { 1, 1, 1 }
        };

        // When
        int sumNeighbors = RuleCheck.countNeighborsForToroidGrid(grid, 0, 1);

        // Then
        Assertions.assertEquals(8, sumNeighbors);
    }

    // Case 3
    @Test
    void countNeighborsForToroidGrid_should_count_neighbors_for_top_right_corner_cell() {
        // Given
        int[][] grid = {
                { 1, 1, 0 },
                { 1, 1, 1 },
                { 1, 1, 1 }
        };

        // When
        int sumNeighbors = RuleCheck.countNeighborsForToroidGrid(grid, 0, 2);

        // Then
        Assertions.assertEquals(8, sumNeighbors);
    }

    // Case 4
    @Test
    void countNeighborsForToroidGrid_should_count_neighbors_for_right_border_cells() {
        // Given
        int[][] grid = {
                { 1, 1, 1 },
                { 1, 1, 0 },
                { 1, 1, 1 }
        };

        // When
        int sumNeighbors = RuleCheck.countNeighborsForToroidGrid(grid, 1, 2);

        // Then
        Assertions.assertEquals(8, sumNeighbors);
    }

    // Case 5
    @Test
    void countNeighborsForToroidGrid_should_count_neighbors_for_bottom_right_corner_cell() {
        // Given
        int[][] grid = {
                { 1, 1, 1 },
                { 1, 1, 1 },
                { 1, 1, 0 }
        };

        // When
        int sumNeighbors = RuleCheck.countNeighborsForToroidGrid(grid, 2, 2);

        // Then
        Assertions.assertEquals(8, sumNeighbors);
    }

    // Case 6
    @Test
    void countNeighborsForToroidGrid_should_count_neighbors_for_bottom_row_cells() {
        // Given
        int[][] grid = {
                { 1, 1, 1 },
                { 1, 1, 1 },
                { 1, 0, 1 }
        };

        // When
        int sumNeighbors = RuleCheck.countNeighborsForToroidGrid(grid, 2, 1);

        // Then
        Assertions.assertEquals(8, sumNeighbors);
    }

    // Case 7
    @Test
    void countNeighborsForToroidGrid_should_count_neighbors_for_bottom_left_corner_cell() {
        // Given
        int[][] grid = {
                { 1, 1, 1 },
                { 1, 1, 1 },
                { 0, 1, 1 }
        };

        // When
        int sumNeighbors = RuleCheck.countNeighborsForToroidGrid(grid, 2, 0);

        // Then
        Assertions.assertEquals(8, sumNeighbors);
    }

    // Case 8
    @Test
    void countNeighborsForToroidGrid_should_count_neighbors_for_left_border_cells() {
        // Given
        int[][] grid = {
                { 1, 1, 1 },
                { 0, 1, 1 },
                { 1, 1, 1 }
        };

        // When
        int sumNeighbors = RuleCheck.countNeighborsForToroidGrid(grid, 1, 0);

        // Then
        Assertions.assertEquals(8, sumNeighbors);
    }
}
