package com.gbtec;

import java.util.List;
public class Main {

    public static void main(String[] args) {
        List<List<Cell>> currentGeneration;
       // Generation generation = new Generation();
        Generation2point0 generation2point0 = new Generation2point0(6);
        //generation.initialize();
        currentGeneration = generation2point0.generation2point0;

        GenerationPrinter.printIntoConsole(currentGeneration);

        NextGeneration nextGeneration = new NextGeneration(generation2point0.generation2point0,6);
        currentGeneration = nextGeneration.generation2point0;
        GenerationPrinter.printIntoConsole(currentGeneration);

    }
}
