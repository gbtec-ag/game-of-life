package com.gbtec;

import java.util.List;

public class GenerationPrinter {

    public static void printIntoConsole(List<List<Cell>> generation) {
        int generationLength = generation.size();
        //int RowLength = generation.get(0).size();
        for (int x = 0; x <= generationLength - 1; x++) {
            List<Cell> currentRow;
            currentRow = generation.get(x);
            String rowOutput = "";

            for (int y = 0; y <= generationLength - 1; y++) {
                Cell currentCell;
                currentCell = currentRow.get(y);
                if (!currentCell.getStatus()) {
                    rowOutput = rowOutput + "o";
                } else {
                    rowOutput = rowOutput + "*";
                }
            }
            System.out.println(rowOutput);
        }
        System.out.println("        ");
    }
}
