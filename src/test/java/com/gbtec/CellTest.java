package com.gbtec;

import com.gbtec.Initialization.Cell;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    @Test
    void CreateCell_should_create_a_living_cell() {
        // given
        Cell cell = new Cell();

        // when
        cell.createCell();

        // then
        boolean expectedBoolean = true;
        assertEquals(expectedBoolean, cell.getStatusOfCell());
    }

    @Test
    void KillCell_should_kill_a_living_cell() {
        // given
        Cell cell = new Cell();

        // when
        cell.createCell();

        // then
        cell.killCell();
        boolean expectedBoolean = false;
        assertEquals(expectedBoolean, cell.getStatusOfCell());
    }

    @Test
    void getStatusOfCell_should_get_status_of_the_cell_() {
        // given
        Cell cell = new Cell();

        // when
        boolean actualBoolean = cell.getStatusOfCell();

        // then
        boolean expectedBoolean = false;
        assertEquals(expectedBoolean, actualBoolean);
    }

}