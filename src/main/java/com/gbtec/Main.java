package com.gbtec;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        int sizeOfGeneration = 4;
        int numberOfNextGenerations =3;

        //Generation initialized
        Generation generation = new Generation(sizeOfGeneration);
        GenerationRandomizer.randomizeGeneration(generation,sizeOfGeneration);
        GenerationPrinter.printIntoConsole(generation);

        for(int i=0; i <=numberOfNextGenerations-2; i++) {
            NextGeneration nextGeneration = new NextGeneration();
            Thread.sleep(2500);
            generation = nextGeneration.calculateNextGeneration(generation, sizeOfGeneration);
            GenerationPrinter.printIntoConsole(generation);
        }
    }
}