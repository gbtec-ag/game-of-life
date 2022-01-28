package com.gbtec.service;

import com.gbtec.model.Cell;
import com.gbtec.model.Generation;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GenerationReaderTest {

    @Test
    void load_initial_generation_from_file() {
        Generation expected = new Generation(Arrays.asList(
                Arrays.asList(new Cell(false), new Cell(false), new Cell(false), new Cell(false), new Cell(false)),
                Arrays.asList(new Cell(false), new Cell(false), new Cell(true), new Cell(false), new Cell(false)),
                Arrays.asList(new Cell(false), new Cell(false), new Cell(true), new Cell(false), new Cell(false)),
                Arrays.asList(new Cell(false), new Cell(false), new Cell(true), new Cell(false), new Cell(false)),
                Arrays.asList(new Cell(false), new Cell(false), new Cell(false), new Cell(false), new Cell(false))
        ));

        Generation actual = GenerationReader.readFromJARFile();

        assertEquals(expected, actual);
    }

}
