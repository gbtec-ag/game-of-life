package com.gbtec;

import java.util.List;
public class Main {

    public static void main(String[] args) {
        List<List<Cell>> currentGeneration;
        Generation generation = new Generation();
        generation.initialize();
        currentGeneration = generation.generation;

        GenerationPrinter.printIntoConsole(currentGeneration);

        NextGeneration nextGeneration = new NextGeneration();
        currentGeneration  = nextGeneration.calculateNextGeneration(currentGeneration);
        GenerationPrinter.printIntoConsole(currentGeneration);
    }
}
