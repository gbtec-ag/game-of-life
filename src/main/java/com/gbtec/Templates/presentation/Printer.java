package com.gbtec.Templates.presentation;

import static org.fusesource.jansi.Ansi.ansi;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.gbtec.Templates.model.Cell;
import com.gbtec.Templates.model.Generation;

import org.fusesource.jansi.AnsiConsole;

public class Printer {

    public static final int WAIT_MILLIS = 1000;

    public Printer() {
        AnsiConsole.systemInstall();
        System.out.println(ansi().eraseScreen().cursorMove(0, 0));
    }

    public void printDemo() throws InterruptedException {
        System.out.println(ansi().eraseLine().cursorUpLine().render("@|yellow X|@ @|white X|@"));
        Thread.sleep(WAIT_MILLIS);
        System.out.println(ansi().eraseLine().cursorUpLine().render("@|white X|@ @|yellow X|@"));
        Thread.sleep(WAIT_MILLIS);
        System.out.println(ansi().eraseLine().cursorUpLine().render("@|yellow X|@ @|white X|@ █▄▀■░"));
        Thread.sleep(WAIT_MILLIS);
    }

    public void printGeneration(Generation generation) {
        System.out.println(ansi().cursorUpLine(generation.getCells().size() + 1));
        generation.getCells().forEach(this::printLine);
        try {
            Thread.sleep(WAIT_MILLIS);
        } catch (InterruptedException ex) {
            // I do not care
        }
    }

    private void printLine(ArrayList<Cell> line) {
        String renderedLine = line.stream().map(this::renderCell).collect(Collectors.joining(""));
        System.out.println(ansi().render(renderedLine));
    }

    private String renderCell(Cell cell) {
        if (cell.getIsAlive()) {
            return "@|yellow X|@";
        } else {
            return "@|white O|@";
        }
    }
}
