package com.gbtec;

import java.util.List;
public class Main {

    public static void main(String[] args) {
        int x= 6;
        int numberOfRepetitions =3;
        List<List<Cell>> currentGeneration;
        Generation2 generation = new Generation2(x-1);
        currentGeneration = generation.generation2;
        GenerationPrinter.printIntoConsole(currentGeneration);

        for(int i=0; i <=numberOfRepetitions-1; i++) {
            NextGeneration2 nextGeneration = new NextGeneration2();
            currentGeneration = nextGeneration.calculateNextGeneration(currentGeneration, x - 1);
            GenerationPrinter.printIntoConsole(currentGeneration);
        }
        /*Generation generation = new Generation();
        generation.initialize();
        currentGeneration = generation.generation;

        GenerationPrinter.printIntoConsole(currentGeneration);

        NextGeneration2 nextGeneration = new NextGeneration2();
        currentGeneration  = nextGeneration.calculateNextGeneration(currentGeneration);
        GenerationPrinter.printIntoConsole(currentGeneration);
*/
    }
}
