package com.gbtec;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GenerationPrinterTest {
    @Test
    void generationPrinter_should_output_given_generation_in_this_example_2x2(){
        //given
        Generation testGeneration = new Generation(2);
        testGeneration.generation.get(0).get(0).setStatus(true);
        testGeneration.generation.get(0).get(1).setStatus(true);
        testGeneration.generation.get(1).get(0).setStatus(false);
        testGeneration.generation.get(1).get(1).setStatus(false);
        //when
        GenerationPrinter.printIntoConsole(testGeneration.generation);
        //then
        System.out.println("OO");
        System.out.println("XX");
    }

    @Test
    void generationPrinter_should_output_given_generation_in_this_example_3x3(){
        //given
        Generation testGeneration = new Generation(3);
        testGeneration.generation.get(0).get(0).setStatus(true);
        testGeneration.generation.get(0).get(1).setStatus(true);
        testGeneration.generation.get(0).get(2).setStatus(false);
        testGeneration.generation.get(1).get(0).setStatus(false);
        testGeneration.generation.get(1).get(1).setStatus(true);
        testGeneration.generation.get(1).get(2).setStatus(true);
        testGeneration.generation.get(2).get(0).setStatus(false);
        testGeneration.generation.get(2).get(1).setStatus(false);
        testGeneration.generation.get(2).get(2).setStatus(true);
        //when
        GenerationPrinter.printIntoConsole(testGeneration.generation);
        //then
        System.out.println("OOX");
        System.out.println("XOO");
        System.out.println("XXO");
    }
}
