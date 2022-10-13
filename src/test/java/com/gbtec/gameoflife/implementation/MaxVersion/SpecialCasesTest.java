package com.gbtec.gameoflife.implementation.MaxVersion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SpecialCasesTest {

    // Case 1
    @Test
    void countNeighborsForBlockedBorders_should_count_neighbors_for_top_left_corner_cell() {
        // given Cell is on zero and has two neighbors (Case 1)

                // @formatter:off
                // Instead of "int[][]" you can also use "boolean[][]"
        int[][] grid = new int[][]{
                {0, 1, 0},
                {1, 0, 0},
                {0, 0, 0}
        };
                // @formatter:on

        // when checked for sum of neighbors
        int sumNeighbors = SpecialCases.countNeighborsForBlockedBorders(grid, 0, 0);

        // then 'sumNeighbors' should hold '2'
        Assertions.assertEquals(2, sumNeighbors);
    }

    // Case 2
    @Test
    void countNeighborsForBlockedBorders_should_count_neighbors_for_top_row_cells() {
        // given

                // @formatter:off
                // Instead of "int[][]" you can also use "boolean[][]"
        int[][] grid = new int[][]{
                {0, 1, 0},
                {1, 0, 0},
                {0, 0, 0}
        };
                // @formatter:on

        // when
        int sumNeighbors = SpecialCases.countNeighborsForBlockedBorders(grid, 0, 1);

        // then
        Assertions.assertEquals(1, sumNeighbors);
    }

    // Case 3
    @Test
    void countNeighborsForBlockedBorders_should_count_neighbors_for_top_right_corner_cell() {
        // given

                // @formatter:off
        int[][] grid = new int[][]{
                {0, 1, 0},
                {1, 0, 1},
                {1, 1, 1}
        };
                // @formatter:on

        // when
        int sumNeighbors = SpecialCases.countNeighborsForBlockedBorders(grid, 0, 2);

        // then
        Assertions.assertEquals(2, sumNeighbors);
    }

    // Case 4
    @Test
    void countNeighborsForBlockedBorders_should_count_neighbors_for_right_border_cells() {
        // given

        // @formatter:off
        int[][] grid = new int[][]{
                {0, 1, 0},
                {1, 0, 1},
                {1, 1, 1}
        };
        // @formatter:on

        // when
        int sumNeighbors = SpecialCases.countNeighborsForBlockedBorders(grid, 1, 2);

        // then
        Assertions.assertEquals(3, sumNeighbors);
    }

    // Case 5
    @Test
    void countNeighborsForBlockedBorders_should_count_neighbors_for_bottom_right_corner_cell() {
        // given

        // @formatter:off
        int[][] grid = new int[][]{
                {0, 1, 0},
                {1, 0, 1},
                {1, 1, 1}
        };
        // @formatter:on

        // when
        int sumNeighbors = SpecialCases.countNeighborsForBlockedBorders(grid, 2, 2);

        // then
        Assertions.assertEquals(2, sumNeighbors);
    }

    // Case 6
    @Test
    void countNeighborsForBlockedBorders_should_count_neighbors_for_bottom_row_cells() {
        // given

        // @formatter:off
        int[][] grid = new int[][]{
                {0, 1, 0},
                {1, 0, 1},
                {1, 1, 1}
        };
        // @formatter:on

        // when
        int sumNeighbors = SpecialCases.countNeighborsForBlockedBorders(grid, 2, 1);

        // then
        Assertions.assertEquals(4, sumNeighbors);
    }

    // Case 7
    @Test
    void countNeighborsForBlockedBorders_should_count_neighbors_for_bottom_left_corner_cell() {
        // given

        // @formatter:off
        int[][] grid = new int[][]{
                {0, 1, 0},
                {1, 0, 1},
                {1, 1, 1}
        };
        // @formatter:on

        // when
        int sumNeighbors = SpecialCases.countNeighborsForBlockedBorders(grid, 2, 0);

        // then
        Assertions.assertEquals(2, sumNeighbors);
    }

    // Case 8
    @Test
    void countNeighborsForBlockedBorders_should_count_neighbors_for_left_border_cells() {
        // given

        // @formatter:off
        int[][] grid = new int[][]{
                {0, 1, 0},
                {1, 0, 1},
                {1, 1, 1}
        };
        // @formatter:on

        // when
        int sumNeighbors = SpecialCases.countNeighborsForBlockedBorders(grid, 1, 0);

        // then
        Assertions.assertEquals(3, sumNeighbors);
    }

    // _________________________________________________________________________________________________________________

    // Case 1
    @Test
    void countNeighborsForToroidGrid_should_count_neighbors_for_top_left_corner_cell() {
        // Given
        int[][] grid = {
                {0, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        };

        // When
        int sumNeighbors = SpecialCases.countNeighborsForToroidGrid(grid, 0, 0);

        // Then
        Assertions.assertEquals(8, sumNeighbors);
    }

    // Case 2
    @Test
    void countNeighborsForToroidGrid_should_count_neighbors_for_top_row_cells() {
        // Given
        int[][] grid = {
                {1, 0, 1},
                {1, 1, 1},
                {1, 1, 1}
        };

        // When
        int sumNeighbors = SpecialCases.countNeighborsForToroidGrid(grid, 0, 1);

        // Then
        Assertions.assertEquals(8, sumNeighbors);
    }

    // Case 3
    @Test
    void countNeighborsForToroidGrid_should_count_neighbors_for_top_right_corner_cell() {
        // Given
        int[][] grid = {
                {1, 1, 0},
                {1, 1, 1},
                {1, 1, 1}
        };

        // When
        int sumNeighbors = SpecialCases.countNeighborsForToroidGrid(grid, 0, 2);

        // Then
        Assertions.assertEquals(8, sumNeighbors);
    }

    // Case 4
    @Test
    void countNeighborsForToroidGrid_should_count_neighbors_for_right_border_cells() {
        // Given
        int[][] grid = {
                {1, 1, 1},
                {1, 1, 0},
                {1, 1, 1}
        };

        // When
        int sumNeighbors = SpecialCases.countNeighborsForToroidGrid(grid, 1, 2);

        // Then
        Assertions.assertEquals(8, sumNeighbors);
    }

    // Case 5
    @Test
    void countNeighborsForToroidGrid_should_count_neighbors_for_bottom_right_corner_cell() {
        // Given
        int[][] grid = {
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 0}
        };

        // When
        int sumNeighbors = SpecialCases.countNeighborsForToroidGrid(grid, 2, 2);

        // Then
        Assertions.assertEquals(8, sumNeighbors);
    }

    // Case 6
    @Test
    void countNeighborsForToroidGrid_should_count_neighbors_for_bottom_row_cells() {
        // Given
        int[][] grid = {
                {1, 1, 1},
                {1, 1, 1},
                {1, 0, 1}
        };

        // When
        int sumNeighbors = SpecialCases.countNeighborsForToroidGrid(grid, 2, 1);

        // Then
        Assertions.assertEquals(8, sumNeighbors);
    }

    // Case 7
    @Test
    void countNeighborsForToroidGrid_should_count_neighbors_for_bottom_left_corner_cell() {
        // Given
        int[][] grid = {
                {1, 1, 1},
                {1, 1, 1},
                {0, 1, 1}
        };

        // When
        int sumNeighbors = SpecialCases.countNeighborsForToroidGrid(grid, 2, 0);

        // Then
        Assertions.assertEquals(8, sumNeighbors);
    }

    // Case 8
    @Test
    void countNeighborsForToroidGrid_should_count_neighbors_for_left_border_cells() {
        // Given
        int[][] grid = {
                {1, 1, 1},
                {0, 1, 1},
                {1, 1, 1}
        };

        // When
        int sumNeighbors = SpecialCases.countNeighborsForToroidGrid(grid, 1, 0);

        // Then
        Assertions.assertEquals(8, sumNeighbors);
    }
}