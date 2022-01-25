package com.gbtec.service;

import com.gbtec.model.Generation;

public class GenerationCalculator {

    public static Generation calculateNextGeneration(Generation currentGeneration) {
        Generation nextGeneration = new Generation();
        nextGeneration.addLine("OXOOX");
        nextGeneration.addLine("XOXOO");
        nextGeneration.addLine("OXOOX");
        nextGeneration.addLine("XOOXO");
        nextGeneration.addLine("OXOOX");
        return nextGeneration;
    }
}
