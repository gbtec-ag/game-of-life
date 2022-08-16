package com.gbtec;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NextGenerationTest {
    @Test
    void calculate_NextGeneration_should_follow_rules(){
        //given
        Generation testGeneration = new Generation(3);
        List<List<Cell>> currentGeneration = new ArrayList<>();
        currentGeneration = testGeneration.generation;
        currentGeneration.get(0).get(0).setStatus(false);
        currentGeneration.get(0).get(1).setStatus(true);
        currentGeneration.get(0).get(2).setStatus(false);
        currentGeneration.get(1).get(0).setStatus(false);
        currentGeneration.get(1).get(1).setStatus(true);
        currentGeneration.get(1).get(2).setStatus(false);
        currentGeneration.get(2).get(0).setStatus(false);
        currentGeneration.get(2).get(1).setStatus(true);
        currentGeneration.get(2).get(2).setStatus(false);
        GenerationPrinter.printIntoConsole(currentGeneration);
        //when
        NextGeneration testNextGeneration = new NextGeneration();
        currentGeneration = testNextGeneration.calculateNextGeneration(testGeneration.generation, 3);
        GenerationPrinter.printIntoConsole(currentGeneration);
        //then
        System.out.println("EXPECTED NEXTGENERATION :");
        System.out.println("XXX");
        System.out.println("OOO");
        System.out.println("XXX");


    }

    @Test
    void count_neighbours_should_detect_left_Neighbour() {

        //given
        Generation testGeneration = new Generation(3);
        testGeneration.generation.get(0).get(0).setStatus(true);
        testGeneration.generation.get(0).get(1).setStatus(true);
        testGeneration.generation.get(0).get(2).setStatus(false);
        testGeneration.generation.get(1).get(0).setStatus(false);
        testGeneration.generation.get(1).get(1).setStatus(false);
        testGeneration.generation.get(1).get(2).setStatus(false);
        testGeneration.generation.get(2).get(0).setStatus(false);
        testGeneration.generation.get(2).get(1).setStatus(false);
        testGeneration.generation.get(2).get(2).setStatus(false);
        //when
        int neighbours;
        neighbours = NextGeneration.countNeighbours(testGeneration.generation, 3, 0, testGeneration.generation.get(0), 3, 1);
        //then
        assertEquals(1, neighbours);
    }

    @Test
    void count_neighbours_should_detect_right_Neighbour() {

        //given
        Generation testGeneration = new Generation(3);
        testGeneration.generation.get(0).get(0).setStatus(true);
        testGeneration.generation.get(0).get(1).setStatus(true);
        testGeneration.generation.get(0).get(2).setStatus(false);
        testGeneration.generation.get(1).get(0).setStatus(false);
        testGeneration.generation.get(1).get(1).setStatus(false);
        testGeneration.generation.get(1).get(2).setStatus(false);
        testGeneration.generation.get(2).get(0).setStatus(false);
        testGeneration.generation.get(2).get(1).setStatus(false);
        testGeneration.generation.get(2).get(2).setStatus(false);
        //when
        int neighbours;
        neighbours = NextGeneration.countNeighbours(testGeneration.generation, 3, 2, testGeneration.generation.get(0), 3, 0);
        //then
        assertEquals(1, neighbours);
    }

    @Test
    void count_neighbours_should_detect_bottom_Neighbour() {

        //given
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
        //when
        int neighbours;
        neighbours = NextGeneration.countNeighbours(testGeneration.generation, 3, 0, testGeneration.generation.get(0), 3, 0);
        //then
        assertEquals(1, neighbours);
    }

    @Test
    void count_neighbours_should_detect_top_Neighbour() {

        //given
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
        //when
        int neighbours;
        neighbours = NextGeneration.countNeighbours(testGeneration.generation, 3, 1, testGeneration.generation.get(0), 3, 0);
        //then
        assertEquals(1, neighbours);
    }

    @Test
    void count_neighbours_should_detect_top_left_Neighbour() {

        //given
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
        //when
        int neighbours;
        neighbours = NextGeneration.countNeighbours(testGeneration.generation, 3, 1, testGeneration.generation.get(1), 3, 1);
        //then
        assertEquals(1, neighbours);
    }

    @Test
    void count_neighbours_should_detect_top_right_Neighbour() {

        //given
        Generation testGeneration = new Generation(2);
        testGeneration.generation.get(0).get(0).setStatus(false);
        testGeneration.generation.get(0).get(1).setStatus(true);
        testGeneration.generation.get(1).get(0).setStatus(true);
        testGeneration.generation.get(1).get(1).setStatus(false);
        //when
        int neighbours;
        neighbours = NextGeneration.countNeighbours(testGeneration.generation, 2, 1, testGeneration.generation.get(1), 2, 0);
        //then
        assertEquals(1, neighbours);
    }

    @Test
    void count_neighbours_should_detect_bottom_left_Neighbour() {

        //given
        Generation testGeneration = new Generation(2);
        testGeneration.generation.get(0).get(0).setStatus(false);
        testGeneration.generation.get(0).get(1).setStatus(true);
        testGeneration.generation.get(1).get(0).setStatus(true);
        testGeneration.generation.get(1).get(1).setStatus(false);
        //when
        int neighbours;
        neighbours = NextGeneration.countNeighbours(testGeneration.generation, 2, 0, testGeneration.generation.get(0), 2, 1);
        //then
        assertEquals(1, neighbours);
    }

    @Test
    void count_neighbours_should_detect_bottom_right_Neighbour() {

        //given
        Generation testGeneration = new Generation(2);
        testGeneration.generation.get(0).get(0).setStatus(true);
        testGeneration.generation.get(0).get(1).setStatus(false);
        testGeneration.generation.get(1).get(0).setStatus(false);
        testGeneration.generation.get(1).get(1).setStatus(true);
        //when
        int neighbours;
        neighbours = NextGeneration.countNeighbours(testGeneration.generation, 2, 0, testGeneration.generation.get(0), 2, 0);
        //then
        assertEquals(1, neighbours);
    }

}
