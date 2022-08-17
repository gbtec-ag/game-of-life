package com.gbtec;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        int sizeOfGeneration = 100;
        int numberOfNextGenerations = 100;
        int wishPattern = 2;


        // Selecting which kind of pattern you want
        Generation generation = new Generation(sizeOfGeneration);
        if (wishPattern == 0) {
            GenerationRandomizer.randomizeGeneration(generation, sizeOfGeneration);
            //introducing the RealisticGenerationPrinter
            RealsticGenerationPrinter printer2 = new RealsticGenerationPrinter();
            printer2.printGeneration(generation,sizeOfGeneration);

            for (int i = 0; i <= numberOfNextGenerations - 1; i++) {
                NextGeneration nextGeneration = new NextGeneration();
                Thread.sleep(500);
                generation = nextGeneration.calculateNextGeneration(generation, sizeOfGeneration);
                printer2.printGeneration(generation,sizeOfGeneration);

            }
        }
        // for createFlower, size of Generation should be greater than 100 or ==100
        else if (wishPattern == 1 && sizeOfGeneration >= 100) {
            numberOfNextGenerations = 54;
            GenerationPattern.createFlower(generation, sizeOfGeneration);
            RealsticGenerationPrinter printer2 = new RealsticGenerationPrinter();
            printer2.printGeneration(generation,sizeOfGeneration);

            for (int i = 0; i <= numberOfNextGenerations - 1; i++) {
                NextGeneration nextGeneration = new NextGeneration();
                // Thread.sleep(500);
                generation = nextGeneration.calculateNextGeneration(generation, sizeOfGeneration);
                printer2.printGeneration(generation,sizeOfGeneration);

            }
        } else if (wishPattern == 2) {
            GenerationPattern.createGlider(generation);
            RealsticGenerationPrinter printer2 = new RealsticGenerationPrinter();
            printer2.printGeneration(generation,sizeOfGeneration);


            for (int i = 0; i <= numberOfNextGenerations - 1; i++) {
                NextGeneration nextGeneration = new NextGeneration();
                // Thread.sleep(500);
                generation = nextGeneration.calculateNextGeneration(generation, sizeOfGeneration);
                printer2.printGeneration(generation,sizeOfGeneration);
            }
        } else {
            System.out.println("------");
            System.out.println("ERROR!");
            System.out.println("------");
        }
    }
}
