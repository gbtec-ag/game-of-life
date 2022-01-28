package com.gbtec;

import static com.gbtec.service.GenerationCalculator.calculateNextGeneration;
import static com.gbtec.service.GenerationReader.createInitialGeneration;

import com.gbtec.model.Generation;
import com.gbtec.presentation.Printer;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Printer printer = new Printer();
        Generation currentGeneration = createInitialGeneration();

        printer.printGeneration(currentGeneration);
        while (true) {
            currentGeneration = calculateNextGeneration(currentGeneration);
            printer.printGeneration(currentGeneration);
        }
    }

}
