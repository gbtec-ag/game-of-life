package com.gbtec;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generation {
    public List<List<Cell>> generation = new ArrayList<>();

    public Generation() {

    }


    public void initialize() {

        Random random = new Random();

        List<Cell> row1 = new ArrayList<>();
        row1.add(new Cell(0, 0, random.nextBoolean()));
        row1.add(new Cell(0, 1, random.nextBoolean()));
        row1.add(new Cell(0, 2, random.nextBoolean()));
        row1.add(new Cell(0, 3, random.nextBoolean()));
        row1.add(new Cell(0, 4, random.nextBoolean()));
        row1.add(new Cell(0, 5, random.nextBoolean()));


        List<Cell> row2 = new ArrayList<>();
        row2.add(new Cell(1, 0, random.nextBoolean()));
        row2.add(new Cell(1, 1, random.nextBoolean()));
        row2.add(new Cell(1, 2, random.nextBoolean()));
        row2.add(new Cell(1, 3, random.nextBoolean()));
        row2.add(new Cell(1, 4, random.nextBoolean()));
        row2.add(new Cell(1, 5, random.nextBoolean()));


        List<Cell> row3 = new ArrayList<>();
        row3.add(new Cell(2, 0, random.nextBoolean()));
        row3.add(new Cell(2, 1, random.nextBoolean()));
        row3.add(new Cell(2, 2, random.nextBoolean()));
        row3.add(new Cell(2, 3, random.nextBoolean()));
        row3.add(new Cell(2, 4, random.nextBoolean()));
        row3.add(new Cell(2, 5, random.nextBoolean()));

        List<Cell> row4 = new ArrayList<>();
        row4.add(new Cell(3, 0, random.nextBoolean()));
        row4.add(new Cell(3, 1, random.nextBoolean()));
        row4.add(new Cell(3, 2, random.nextBoolean()));
        row4.add(new Cell(3, 3, random.nextBoolean()));
        row4.add(new Cell(3, 4, random.nextBoolean()));
        row4.add(new Cell(3, 5, random.nextBoolean()));

        List<Cell> row5 = new ArrayList<>();
        row5.add(new Cell(4, 0, random.nextBoolean()));
        row5.add(new Cell(4, 1, random.nextBoolean()));
        row5.add(new Cell(4, 2, random.nextBoolean()));
        row5.add(new Cell(4, 3, random.nextBoolean()));
        row5.add(new Cell(4, 4, random.nextBoolean()));
        row5.add(new Cell(4, 5, random.nextBoolean()));

        List<Cell> row6 = new ArrayList<>();
        row6.add(new Cell(5, 0, random.nextBoolean()));
        row6.add(new Cell(5, 1, random.nextBoolean()));
        row6.add(new Cell(5, 2, random.nextBoolean()));
        row6.add(new Cell(5, 3, random.nextBoolean()));
        row6.add(new Cell(5, 4, random.nextBoolean()));
        row6.add(new Cell(5, 5, random.nextBoolean()));


        generation.add(row1);
        generation.add(row2);
        generation.add(row3);
        generation.add(row4);
        generation.add(row5);
        generation.add(row6);
    }


    public void printStatus() {
        for (int x = 0; x < 6; x++) {
            List<Cell> currentRow;
            currentRow = generation.get(x);
            String rowOutput = "";


            for (int y = 0; y < 6; y++) {
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

    }
}


