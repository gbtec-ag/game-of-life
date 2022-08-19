package com.gbtec.controlsystem;

import com.gbtec.model.Generation;
import com.gbtec.initialization.GenerationPattern;
import com.gbtec.initialization.GenerationRandomizer;
import com.gbtec.initialization.NextGeneration;
import com.gbtec.presentation.GenerationPrinter;

public class GenerationController {

    public GenerationController(int wishPattern, int sizeOfGeneration, int numberOfNextGenerations) throws InterruptedException {

        Generation generation = new Generation(sizeOfGeneration);
        if (wishPattern == 0) {
            GenerationRandomizer.randomizeGeneration(generation, sizeOfGeneration);
            initializePrinter(generation, sizeOfGeneration);
            creatingNextGeneration(generation, numberOfNextGenerations, sizeOfGeneration);

        }
        // for createFlower, size of Generation should be greater than 100 or ==100
        else if (wishPattern == 1) {
            if (sizeOfGeneration < 100) {
                int minimumSizeGeneration = 50 - sizeOfGeneration;
                sizeOfGeneration = sizeOfGeneration + minimumSizeGeneration;
            }
            numberOfNextGenerations = 54;
            GenerationPattern.createFlower(generation, sizeOfGeneration);
            initializePrinter(generation, sizeOfGeneration);
            creatingNextGeneration(generation, numberOfNextGenerations, sizeOfGeneration);

        } else if (wishPattern == 2) {
            GenerationPattern.createGlider(generation);
            initializePrinter(generation, sizeOfGeneration);
            creatingNextGeneration(generation, numberOfNextGenerations, sizeOfGeneration);

        } else {
            System.out.println("NOT POSSIBLE");
        }
    }

    private void creatingNextGeneration(Generation generation, int loopSize, int sizeOfGeneration) throws InterruptedException {

        for (int i = 0; i <= loopSize - 1; i++) {

            GenerationPrinter printer = new GenerationPrinter();
            NextGeneration nextGeneration = new NextGeneration();
            generation = nextGeneration.calculateNextGeneration(generation, sizeOfGeneration);
            printer.printGeneration(generation, sizeOfGeneration);
        }
    }

    private void initializePrinter(Generation generation, int sizeOfGeneration) throws InterruptedException {
        GenerationPrinter printer = new GenerationPrinter();
        printer.printGeneration(generation, sizeOfGeneration);
    }
}
