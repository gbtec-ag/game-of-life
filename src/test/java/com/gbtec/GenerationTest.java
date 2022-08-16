package com.gbtec;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class GenerationTest {

    @Test
    void generation_should_create_two_dimensional_list_of_Cells_with_size_3x3() {
        // given
        Generation testGeneration = new Generation(3);
        // when
        boolean status = testGeneration.generation.get(2).get(2).getStatus();
        // then
        if (!status) {
            assertFalse(false);
        } else {
            assertTrue(true);
        }
    }

    @Test
    void generation_should_create_two_dimensional_list_of_Cells_with_size_20x20() {
        // given
        Generation testGeneration = new Generation(20);
       // when
        boolean status = testGeneration.generation.get(19).get(19).getStatus();
        // then
        if (!status) {
            assertFalse(false);
        } else {
            assertTrue(true);
        }
    }
}
