package com.gbtec;

import java.util.List;

public class GenerationPrinter {

    public static void printIntoConsole(Generation generation) {
        int generationLength = generation.generation.size();
        for (int x = 0; x <= generationLength - 1; x++) {
            List<Cell> currentRow;
            currentRow = generation.generation.get(x);
            String rowOutput = "";

            for (int y = 0; y <= generationLength - 1; y++) {
                Cell currentCell;
                currentCell = currentRow.get(y);
                if (!currentCell.getStatus()) {
                    rowOutput = rowOutput + "-";
                } else {
                    rowOutput = rowOutput + "o";
                }
            }
            System.out.println(rowOutput);
        }
        System.out.println("        ");
    }
}
