package com.gbtec;

import static org.fusesource.jansi.Ansi.ansi;

import java.util.ArrayList;
import java.util.List;
import org.fusesource.jansi.AnsiConsole;
public class GenerationPrinter {

    public static final int WAIT_MILLIS = 500;

    public GenerationPrinter() {
        AnsiConsole.systemInstall();
        System.out.println(ansi().eraseScreen().cursorMove(0, 0));
    }

    public void printGeneration(Generation generation, int size) throws InterruptedException {
        List<String> outputString = renderGeneration(generation, size);
        int i = 0;
        Thread.sleep(WAIT_MILLIS);
        System.out.println(ansi().eraseScreen());
        while (i <= size - 1) {
            System.out.println(ansi().render(outputString.get(i)));
            i++;
        }

    }

    private List<String> renderGeneration(Generation generation, int size) {
        // Taking the two-dimensional List in order to get to the cells
        List<String> renderedGeneration = new ArrayList<>();
        for (int i = 0; i <= size - 1; i++) {
            String line="";
            for (int j = 0; j <= size - 1; j++) {
                Cell currentCell = generation.getCell(i,j);
                if (!currentCell.getStatusOfCell()) {
                    line = line + " ";
                } else
                    line = line + "@|red O|@";
            }
            renderedGeneration.add(line);
        }
        return renderedGeneration;
    }
}
