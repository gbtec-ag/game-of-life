package com.gbtec.service;

import com.gbtec.model.Generation;

import java.io.*;

public class GenerationReader {

    public static Generation createInitialGeneration() throws FileNotFoundException {
        File file = new File("C:\\Users\\jonathan.kampmann\\IdeaProjects\\game-of-life\\src\\main\\resources\\firstGeneration.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        Generation generation = new Generation();
        reader.lines().forEach(generation::addLine);
        return generation;
    }
}
