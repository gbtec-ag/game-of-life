package com.gbtec;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generation {
    public List<List<Cell>> generation = new ArrayList<>();

    public Generation() {}

// List<List<>> gets filled with Cells
    public void initialize() {

        Random random = new Random();

        List<Cell> row1 = new ArrayList<>();
        row1.add(new Cell(false));
        row1.add(new Cell(true));
        row1.add(new Cell(false));
        /*row1.add(new Cell(false));
        row1.add(new Cell(false));
        row1.add(new Cell(false));*/


        List<Cell> row2 = new ArrayList<>();
        row2.add(new Cell(false));
        row2.add(new Cell(true));
        row2.add(new Cell(false));
        /*row2.add(new Cell(false));
        row2.add(new Cell(false));
        row2.add(new Cell(false));*/


        List<Cell> row3 = new ArrayList<>();
        row3.add(new Cell(false));
        row3.add(new Cell(true));
        row3.add(new Cell(false));
        /*row3.add(new Cell(false));
        row3.add(new Cell(false));
        row3.add(new Cell(false));*/

        /*List<Cell> row4 = new ArrayList<>();
        row4.add(new Cell(false));
        row4.add(new Cell(true));
        row4.add(new Cell(false));
        row4.add(new Cell(false));
        row4.add(new Cell(false));
        row4.add(new Cell(false));

        List<Cell> row5 = new ArrayList<>();
        row5.add(new Cell(false));
        row5.add(new Cell(true));
        row5.add(new Cell(false));
        row5.add(new Cell(false));
        row5.add(new Cell(false));
        row5.add(new Cell(false));

        List<Cell> row6 = new ArrayList<>();
        row6.add(new Cell(false));
        row6.add(new Cell(true));
        row6.add(new Cell(false));
        row6.add(new Cell(false));
        row6.add(new Cell(false));
        row6.add(new Cell(false));

         */


        generation.add(row1);
        generation.add(row2);
        generation.add(row3);
        /*generation.add(row4);
        generation.add(row5);
        generation.add(row6);*/
    }


    public void printStatus() {
        for (int x = 0; x <= 2; x++) {
            List<Cell> currentRow;
            currentRow = generation.get(x);
            String rowOutput = "";


            for (int y = 0; y <= 2; y++) {
                Cell currentCell;
                currentCell = currentRow.get(y);
                if (!currentCell.getStatus()) {
                    rowOutput = rowOutput + "X";
                } else {
                    rowOutput = rowOutput + "0";
                }
            }
            System.out.println(rowOutput);

        }
        System.out.println("      ");
    }
}


