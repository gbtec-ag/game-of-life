package com.gbtec;

import java.util.List;
public class Main {

    public static void main(String[] args) throws InterruptedException {
        int sizeOfGeneration = 5;
        int numberOfNextGenerations =6;
        List<List<Cell>> currentGeneration;
        Generation generation = new Generation(sizeOfGeneration);
        currentGeneration = generation.generation;
        GenerationPrinter.printIntoConsole(currentGeneration);

        for(int i=0; i <=numberOfNextGenerations-2; i++) {
            NextGeneration nextGeneration = new NextGeneration();
            Thread.sleep(2500);
            currentGeneration = nextGeneration.calculateNextGeneration(currentGeneration, sizeOfGeneration);
            GenerationPrinter.printIntoConsole(currentGeneration);
        }
    }
}
//