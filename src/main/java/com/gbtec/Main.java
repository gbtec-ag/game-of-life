package com.gbtec;

import static com.gbtec.service.GenerationCalculator.calculateNextGeneration;

import com.gbtec.model.Generation;
import com.gbtec.presentation.Printer;
import com.gbtec.service.GenerationReader;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Printer printer = new Printer();
        Generation currentGeneration = GenerationReader.readFromJARFile();

        printer.printGeneration(currentGeneration);
        while (true) {
            currentGeneration = calculateNextGeneration(currentGeneration);
            printer.printGeneration(currentGeneration);
        }
    }

}
