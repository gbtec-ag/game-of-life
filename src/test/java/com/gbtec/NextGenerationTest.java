package com.gbtec;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class NextGenerationTest {

    GenerationPrinter printer = new GenerationPrinter();

    @Test
    void calculate_NextGeneration_should_follow_rules() throws InterruptedException, IOException {
        // given
        Generation testGeneration = new Generation(3);
        testGeneration.getGeneration().get(0).get(1).setStatus(true);
        testGeneration.getGeneration().get(1).get(1).setStatus(true);
        testGeneration.getGeneration().get(2).get(1).setStatus(true);

        // when
        NextGeneration testNextGeneration = new NextGeneration();
        testGeneration = testNextGeneration.calculateNextGeneration(testGeneration, 3);
        printer.printGeneration(testGeneration, 3);
        // then
        Generation resultGeneration = new Generation(3);
        resultGeneration.getGeneration().get(1).get(0).setStatus(true);
        resultGeneration.getGeneration().get(1).get(1).setStatus(true);
        resultGeneration.getGeneration().get(1).get(2).setStatus(true);
        printer.printGeneration(resultGeneration, 3);

        for (int i = 0; i <= testGeneration.getGeneration().size() - 1; i++) {
            for (int j = 0; j <= testGeneration.getGeneration().size() - 1; j++) {
                assertEquals(resultGeneration.getGeneration().get(i).get(j).getStatus(),
                    testGeneration.getGeneration().get(i).get(j).getStatus());
            }

        }
    }

    @Test
    void calculate_NextGeneration_should_output_first_Generation_after_two_NextGeneration_runs() throws InterruptedException,IOException {
        // given
        Generation testGeneration = new Generation(3);
        testGeneration.getGeneration().get(0).get(1).setStatus(true);
        testGeneration.getGeneration().get(1).get(1).setStatus(true);
        testGeneration.getGeneration().get(2).get(1).setStatus(true);
        printer.printGeneration(testGeneration, 3);
        // when
        NextGeneration testNextGeneration = new NextGeneration();
        for (int i = 0; i <= 1; i++) {
            testGeneration = testNextGeneration.calculateNextGeneration(testGeneration, 3);
            printer.printGeneration(testGeneration, 3);
        }
        // then
        Generation resultGeneration = new Generation(3);
        resultGeneration.getGeneration().get(0).get(1).setStatus(true);
        resultGeneration.getGeneration().get(1).get(1).setStatus(true);
        resultGeneration.getGeneration().get(2).get(1).setStatus(true);
        System.out.println("--------");
        System.out.println("EXPECTED");
        System.out.println("--------");
        printer.printGeneration(resultGeneration, 3);

        for (int i = 0; i <= testGeneration.getGeneration().size() - 1; i++) {
            for (int j = 0; j <= testGeneration.getGeneration().size() - 1; j++) {
                assertEquals(resultGeneration.getGeneration().get(i).get(j).getStatus(),
                    testGeneration.getGeneration().get(i).get(j).getStatus());
            }

        }
    }

    @Test
    void count_neighbours_should_detect_left_Neighbour() {

        // given
        Generation testGeneration = new Generation(3);
        testGeneration.getGeneration().get(0).get(0).setStatus(true);
        testGeneration.getGeneration().get(0).get(1).setStatus(true);
        // when
        int neighbours;
        neighbours = NextGeneration.countNeighbours(testGeneration, 3, 0, testGeneration.getGeneration().get(0), 3, 1);
        // then
        assertEquals(1, neighbours);
    }

    @Test
    void count_neighbours_should_detect_right_Neighbour() {

        // given
        Generation testGeneration = new Generation(3);
        testGeneration.getGeneration().get(0).get(0).setStatus(true);
        testGeneration.getGeneration().get(0).get(1).setStatus(true);
        // when
        int neighbours;
        neighbours = NextGeneration.countNeighbours(testGeneration, 3, 2, testGeneration.getGeneration().get(0), 3, 0);
        // then
        assertEquals(1, neighbours);
    }

    @Test
    void count_neighbours_should_detect_bottom_Neighbour() {

        // given
        Generation testGeneration = new Generation(3);
        testGeneration.getGeneration().get(0).get(0).setStatus(true);
        testGeneration.getGeneration().get(1).get(0).setStatus(true);
        // when
        int neighbours;
        neighbours = NextGeneration.countNeighbours(testGeneration, 3, 0, testGeneration.getGeneration().get(0), 3, 0);
        // then
        assertEquals(1, neighbours);
    }

    @Test
    void count_neighbours_should_detect_top_Neighbour() {

        // given
        Generation testGeneration = new Generation(3);
        testGeneration.getGeneration().get(0).get(0).setStatus(true);
        testGeneration.getGeneration().get(1).get(0).setStatus(true);
        // when
        int neighbours;
        neighbours = NextGeneration.countNeighbours(testGeneration, 3, 1, testGeneration.getGeneration().get(0), 3, 0);
        // then
        assertEquals(1, neighbours);
    }

    @Test
    void count_neighbours_should_detect_top_left_Neighbour() {

        // given
        Generation testGeneration = new Generation(3);
        testGeneration.getGeneration().get(0).get(0).setStatus(true);
        testGeneration.getGeneration().get(1).get(1).setStatus(true);

        // when
        int neighbours;
        neighbours = NextGeneration.countNeighbours(testGeneration, 3, 1, testGeneration.getGeneration().get(1), 3, 1);
        // then
        assertEquals(1, neighbours);
    }

    @Test
    void count_neighbours_should_detect_top_right_Neighbour() {

        // given
        Generation testGeneration = new Generation(2);
        testGeneration.getGeneration().get(0).get(1).setStatus(true);
        testGeneration.getGeneration().get(1).get(0).setStatus(true);
        // when
        int neighbours;
        neighbours = NextGeneration.countNeighbours(testGeneration, 2, 1, testGeneration.getGeneration().get(1), 2, 0);
        // then
        assertEquals(1, neighbours);
    }

    @Test
    void count_neighbours_should_detect_bottom_left_Neighbour() {

        // given
        Generation testGeneration = new Generation(2);
        testGeneration.getGeneration().get(0).get(1).setStatus(true);
        testGeneration.getGeneration().get(1).get(0).setStatus(true);
        // when
        int neighbours;
        neighbours = NextGeneration.countNeighbours(testGeneration, 2, 0, testGeneration.getGeneration().get(0), 2, 1);
        // then
        assertEquals(1, neighbours);
    }

    @Test
    void count_neighbours_should_detect_bottom_right_Neighbour() {

        // given
        Generation testGeneration = new Generation(2);
        testGeneration.getGeneration().get(0).get(0).setStatus(true);
        testGeneration.getGeneration().get(1).get(1).setStatus(true);
        // when
        int neighbours;
        neighbours = NextGeneration.countNeighbours(testGeneration, 2, 0, testGeneration.getGeneration().get(0), 2, 0);
        // then
        assertEquals(1, neighbours);
    }

}
