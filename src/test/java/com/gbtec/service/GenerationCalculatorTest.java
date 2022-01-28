package com.gbtec.service;

import com.gbtec.model.Cell;
import com.gbtec.model.Generation;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GenerationCalculatorTest {

    @Test
    void test_generate_next_generation (){
        Generation expected = new Generation(Arrays.asList(
                Arrays.asList(new Cell(false), new Cell(true), new Cell(false)),
                Arrays.asList(new Cell(false), new Cell(true), new Cell(false)),
                Arrays.asList(new Cell(false), new Cell(true), new Cell(false))
        ));

        Generation input = new Generation(Arrays.asList(
                Arrays.asList(new Cell(false), new Cell(false), new Cell(false)),
                Arrays.asList(new Cell(true), new Cell(true), new Cell(true)),
                Arrays.asList(new Cell(false), new Cell(false), new Cell(false))
        ));

        Generation actual = GenerationCalculator.calculateNextGeneration( input);
        assertEquals(expected, actual) ;
    }

}