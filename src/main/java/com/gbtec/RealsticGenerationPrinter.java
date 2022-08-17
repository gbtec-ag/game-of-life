package com.gbtec;

import static org.fusesource.jansi.Ansi.ansi;

import java.util.ArrayList;
import java.util.List;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

public class RealsticGenerationPrinter {

    public static final int WAIT_MILLIS = 500;

    public RealsticGenerationPrinter() {
        //System.out.println(ansi().eraseScreen().cursorMove(1000, 1000));
    }

    public void printDemo() throws InterruptedException {
        System.out.println(ansi().eraseLine().cursorUpLine().render("@|yellow X|@ @|white X|@"));
        Thread.sleep(WAIT_MILLIS);
        System.out.println(ansi().eraseLine().cursorUpLine().render("@|white X|@ @|yellow X|@"));
        Thread.sleep(WAIT_MILLIS);
        System.out.println(ansi().eraseLine().cursorUpLine().render("@|yellow X|@ @|white X|@ █▄▀■░"));
        Thread.sleep(WAIT_MILLIS);
    }

    public void printGeneration(Generation generation, int size) throws InterruptedException {
        List<String> outputString = renderGeneration(generation, size);
        int i = 0;
        while (i <= size - 1) {
            System.out.println(ansi().render(outputString.get(i)));
            i++;
        }
        Thread.sleep(WAIT_MILLIS);
        AnsiConsole.systemInstall();
        Ansi ansi = Ansi.ansi();
        System.out.println(ansi.eraseScreen());
        System.out.println(ansi.cursorMove(0,0));
        AnsiConsole.systemUninstall();


    }

    private List<String> renderGeneration(Generation generation, int size) {
        // Taking the two-dimensional List in order to get to the cells
        List<List<Cell>> currentGeneration = generation.generation;
        List<String> renderedGeneration = new ArrayList<>();
        for (int i = 0; i <= size - 1; i++) {
            List<Cell> currentRow = currentGeneration.get(i);
            String line = "";
            for (int j = 0; j <= size - 1; j++) {
                Cell currentCell = currentRow.get(j);
                if (currentCell.getStatus() == false) {
                    line = line + "@|white -|@";
                } else
                    line = line + "@|blue o|@";
            }
            renderedGeneration.add(line);
        }
        return renderedGeneration;
    }
}
