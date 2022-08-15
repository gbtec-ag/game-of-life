package com.gbtec;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    @Test
    void setStatus_should_set_the_status_to_true_if_true_is_given(){
        // given
        Cell cell = new Cell(false);

        // when
        cell.setStatus(true);

        // then
        assertTrue(cell.isAlive);
    }

    @Test
    void setStatus_should_set_the_status_to_false_if_false_is_given(){
        // given
        Cell cell = new Cell(true);

        // when
        cell.setStatus(false);

        // then
        assertFalse(cell.isAlive);
    }
}