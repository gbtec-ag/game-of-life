package com.gbtec;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GenerationPrinterTest {

    @Test
    void generationPrinter_should_output_given_generation_in_this_example_2x2() {
        // given

        Generation testGeneration = new Generation(2);
        testGeneration.generation.get(0).get(0).setStatus(true);
        testGeneration.generation.get(0).get(1).setStatus(true);
        // when
        GenerationPrinter.printIntoConsole(testGeneration);
        // then
        Generation resultGeneration = new Generation(2);
        resultGeneration.generation.get(0).get(0).setStatus(true);
        resultGeneration.generation.get(0).get(1).setStatus(true);

        for (int i = 0; i <= testGeneration.generation.size() - 1; i++) {
            for (int j = 0; j <= testGeneration.generation.size() - 1; j++) {
                assertEquals(resultGeneration.generation.get(i).get(j).getStatus(),
                    testGeneration.generation.get(i).get(j).getStatus());
            }
        }
    }

    @Test
    void generationPrinter_should_output_given_generation_in_this_example_3x3() {
        // given
        Generation testGeneration = new Generation(3);
        testGeneration.generation.get(0).get(0).setStatus(true);
        testGeneration.generation.get(0).get(1).setStatus(true);
        testGeneration.generation.get(1).get(1).setStatus(true);
        testGeneration.generation.get(1).get(2).setStatus(true);
        testGeneration.generation.get(2).get(2).setStatus(true);
        // when
        GenerationPrinter.printIntoConsole(testGeneration);
        // then
        Generation resultGeneration = new Generation(3);
        resultGeneration.generation.get(0).get(0).setStatus(true);
        resultGeneration.generation.get(0).get(1).setStatus(true);
        resultGeneration.generation.get(1).get(1).setStatus(true);
        resultGeneration.generation.get(1).get(2).setStatus(true);
        resultGeneration.generation.get(2).get(2).setStatus(true);

        for (int i = 0; i <= testGeneration.generation.size() - 1; i++) {
            for (int j = 0; j <= testGeneration.generation.size() - 1; j++) {
                assertEquals(resultGeneration.generation.get(i).get(j).getStatus(),
                    testGeneration.generation.get(i).get(j).getStatus());
            }
        }
    }
}
