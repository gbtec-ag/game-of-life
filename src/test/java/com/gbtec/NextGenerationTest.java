package com.gbtec;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NextGenerationTest {

    @Test
    void calculate_NextGeneration_should_follow_rules() {
        // given
        Generation testGeneration = new Generation(3);
        testGeneration.generation.get(0).get(1).setStatus(true);
        testGeneration.generation.get(1).get(1).setStatus(true);
        testGeneration.generation.get(2).get(1).setStatus(true);
        GenerationPrinter.printIntoConsole(testGeneration);
        // when
        NextGeneration testNextGeneration = new NextGeneration();
        testGeneration = testNextGeneration.calculateNextGeneration(testGeneration, 3);
        GenerationPrinter.printIntoConsole(testGeneration);
        // then
        Generation resultGeneration = new Generation(3);
        resultGeneration.generation.get(1).get(0).setStatus(true);
        resultGeneration.generation.get(1).get(1).setStatus(true);
        resultGeneration.generation.get(1).get(2).setStatus(true);
        GenerationPrinter.printIntoConsole(resultGeneration);

        for (int i = 0; i <= testGeneration.generation.size() - 1; i++) {
            for (int j = 0; j <= testGeneration.generation.size() - 1; j++) {
                assertEquals(resultGeneration.generation.get(i).get(j).getStatus(),
                    testGeneration.generation.get(i).get(j).getStatus());
            }

        }
    }

    @Test
    void calculate_NextGeneration_should_output_first_Generation_after_two_NextGeneration_runs() {
        // given
        Generation testGeneration = new Generation(3);
        testGeneration.generation.get(0).get(1).setStatus(true);
        testGeneration.generation.get(1).get(1).setStatus(true);
        testGeneration.generation.get(2).get(1).setStatus(true);
        GenerationPrinter.printIntoConsole(testGeneration);
        // when
        NextGeneration testNextGeneration = new NextGeneration();
        for (int i = 0; i <= 1; i++) {
            testGeneration = testNextGeneration.calculateNextGeneration(testGeneration, 3);
            GenerationPrinter.printIntoConsole(testGeneration);
        }
        // then
        Generation resultGeneration = new Generation(3);
        resultGeneration.generation.get(0).get(1).setStatus(true);
        resultGeneration.generation.get(1).get(1).setStatus(true);
        resultGeneration.generation.get(2).get(1).setStatus(true);
        System.out.println("--------");
        System.out.println("EXPECTED");
        System.out.println("--------");
        GenerationPrinter.printIntoConsole(resultGeneration);

        for (int i = 0; i <= testGeneration.generation.size() - 1; i++) {
            for (int j = 0; j <= testGeneration.generation.size() - 1; j++) {
                assertEquals(resultGeneration.generation.get(i).get(j).getStatus(),
                    testGeneration.generation.get(i).get(j).getStatus());
            }

        }
    }

    @Test
    void count_neighbours_should_detect_left_Neighbour() {

        // given
        Generation testGeneration = new Generation(3);
        testGeneration.generation.get(0).get(0).setStatus(true);
        testGeneration.generation.get(0).get(1).setStatus(true);
        // when
        int neighbours;
        neighbours = NextGeneration.countNeighbours(testGeneration, 3, 0, testGeneration.generation.get(0), 3, 1);
        // then
        assertEquals(1, neighbours);
    }

    @Test
    void count_neighbours_should_detect_right_Neighbour() {

        // given
        Generation testGeneration = new Generation(3);
        testGeneration.generation.get(0).get(0).setStatus(true);
        testGeneration.generation.get(0).get(1).setStatus(true);
        // when
        int neighbours;
        neighbours = NextGeneration.countNeighbours(testGeneration, 3, 2, testGeneration.generation.get(0), 3, 0);
        // then
        assertEquals(1, neighbours);
    }

    @Test
    void count_neighbours_should_detect_bottom_Neighbour() {

        // given
        Generation testGeneration = new Generation(3);
        testGeneration.generation.get(0).get(0).setStatus(true);
        testGeneration.generation.get(1).get(0).setStatus(true);
        // when
        int neighbours;
        neighbours = NextGeneration.countNeighbours(testGeneration, 3, 0, testGeneration.generation.get(0), 3, 0);
        // then
        assertEquals(1, neighbours);
    }

    @Test
    void count_neighbours_should_detect_top_Neighbour() {

        // given
        Generation testGeneration = new Generation(3);
        testGeneration.generation.get(0).get(0).setStatus(true);
        testGeneration.generation.get(0).get(1).setStatus(false);
        testGeneration.generation.get(0).get(2).setStatus(false);
        testGeneration.generation.get(1).get(0).setStatus(true);
        testGeneration.generation.get(1).get(1).setStatus(false);
        testGeneration.generation.get(1).get(2).setStatus(false);
        testGeneration.generation.get(2).get(0).setStatus(false);
        testGeneration.generation.get(2).get(1).setStatus(false);
        testGeneration.generation.get(2).get(2).setStatus(false);
        // when
        int neighbours;
        neighbours = NextGeneration.countNeighbours(testGeneration, 3, 1, testGeneration.generation.get(0), 3, 0);
        // then
        assertEquals(1, neighbours);
    }

    @Test
    void count_neighbours_should_detect_top_left_Neighbour() {

        // given
        Generation testGeneration = new Generation(3);
        testGeneration.generation.get(0).get(0).setStatus(true);
        testGeneration.generation.get(0).get(1).setStatus(false);
        testGeneration.generation.get(0).get(2).setStatus(false);
        testGeneration.generation.get(1).get(0).setStatus(false);
        testGeneration.generation.get(1).get(1).setStatus(true);
        testGeneration.generation.get(1).get(2).setStatus(false);
        testGeneration.generation.get(2).get(0).setStatus(false);
        testGeneration.generation.get(2).get(1).setStatus(false);
        testGeneration.generation.get(2).get(2).setStatus(false);
        // when
        int neighbours;
        neighbours = NextGeneration.countNeighbours(testGeneration, 3, 1, testGeneration.generation.get(1), 3, 1);
        // then
        assertEquals(1, neighbours);
    }

    @Test
    void count_neighbours_should_detect_top_right_Neighbour() {

        // given
        Generation testGeneration = new Generation(2);
        testGeneration.generation.get(0).get(0).setStatus(false);
        testGeneration.generation.get(0).get(1).setStatus(true);
        testGeneration.generation.get(1).get(0).setStatus(true);
        testGeneration.generation.get(1).get(1).setStatus(false);
        // when
        int neighbours;
        neighbours = NextGeneration.countNeighbours(testGeneration, 2, 1, testGeneration.generation.get(1), 2, 0);
        // then
        assertEquals(1, neighbours);
    }

    @Test
    void count_neighbours_should_detect_bottom_left_Neighbour() {

        // given
        Generation testGeneration = new Generation(2);
        testGeneration.generation.get(0).get(0).setStatus(false);
        testGeneration.generation.get(0).get(1).setStatus(true);
        testGeneration.generation.get(1).get(0).setStatus(true);
        testGeneration.generation.get(1).get(1).setStatus(false);
        // when
        int neighbours;
        neighbours = NextGeneration.countNeighbours(testGeneration, 2, 0, testGeneration.generation.get(0), 2, 1);
        // then
        assertEquals(1, neighbours);
    }

    @Test
    void count_neighbours_should_detect_bottom_right_Neighbour() {

        // given
        Generation testGeneration = new Generation(2);
        testGeneration.generation.get(0).get(0).setStatus(true);
        testGeneration.generation.get(0).get(1).setStatus(false);
        testGeneration.generation.get(1).get(0).setStatus(false);
        testGeneration.generation.get(1).get(1).setStatus(true);
        // when
        int neighbours;
        neighbours = NextGeneration.countNeighbours(testGeneration, 2, 0, testGeneration.generation.get(0), 2, 0);
        // then
        assertEquals(1, neighbours);
    }

}
