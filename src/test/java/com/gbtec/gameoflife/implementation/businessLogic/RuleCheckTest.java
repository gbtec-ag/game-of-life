package com.gbtec.gameoflife.implementation.businessLogic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RuleCheckTest {

    @ParameterizedTest
    @CsvSource({
            "1,1,0", // live cell should die with 1 neighbor
            "1,2,1", // live cell should stay alive with 2 neighbors
            "1,3,1", // live cell should stay alive with 3 neighbors
            "1,4,0", // live cell should die with 4 neighbors
            "0,2,0", // dead cell should stay dead with 2 neighbors
            "0,3,1", // dead cell should be reborn with 3 neighbors
            "0,4,0", // dead cell should stay dead with 4 neighbors
    })
    void should_calculate_next_cell_correctly(String currentCell, String sumNeighbors, String expected) {
        // When
        int result = RuleCheck.checkRules(Integer.parseInt(currentCell), Integer.parseInt(sumNeighbors));

        // Then
        Assertions.assertEquals(Integer.parseInt(expected), result);
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
