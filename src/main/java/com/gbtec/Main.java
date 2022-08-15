package com.gbtec;

import java.util.List;
public class Main {

    public static void main(String[] args) throws InterruptedException {
        int sizeOfGeneration = 9;
        int numberOfNextGenerations =3;
        List<List<Cell>> currentGeneration;
        Generation generation = new Generation(sizeOfGeneration-1);
        currentGeneration = generation.generation;
        GenerationPrinter.printIntoConsole(currentGeneration);

        for(int i=0; i <=numberOfNextGenerations-2; i++) {
            NextGeneration nextGeneration = new NextGeneration();
            Thread.sleep(5000);
            currentGeneration = nextGeneration.calculateNextGeneration(currentGeneration, sizeOfGeneration - 1);
            GenerationPrinter.printIntoConsole(currentGeneration);
        }
    }
}
