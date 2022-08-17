package com.gbtec;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CellTest {

    @Test
    void setStatus_should_set_the_status_to_true_if_true_is_given() {
        // given
        Cell cell = new Cell();

        // when
        cell.setStatus(true);

        // then
        assertTrue(cell.getStatus());
    }

    @Test
    void setStatus_should_set_the_status_to_false_if_false_is_given() {
        // given
        Cell cell = new Cell();

        // when
        cell.setStatus(false);

        // then
        assertFalse(cell.getStatus());
    }

    @Test
    void getStatus_should_get_Status_if_status_is_true() {
        // given
        Cell cell = new Cell();

        // when
        cell.setStatus(true);
        boolean status = cell.getStatus();

        // then
        assertTrue(status);
    }

    @Test
    void getStatus_should_get_Status_if_status_is_false() {
        // given
        Cell cell = new Cell();

        // when
        boolean status = cell.getStatus();

        // then
        assertFalse(status);
    }
}
