package com.gbtec;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GenerationPrinterTest {
    @Test
    void generationPrinter_should_output_given_generation_in_this_example_4x4(){
        Generation testGeneration = new Generation(4);
        GenerationPrinter.printIntoConsole(testGeneration.generation);
    }

    @Test
    void generationPrinter_should_output_given_generation_in_this_example_8x8(){
        Generation testGeneration = new Generation(8);
        GenerationPrinter.printIntoConsole(testGeneration.generation);
    }
}
