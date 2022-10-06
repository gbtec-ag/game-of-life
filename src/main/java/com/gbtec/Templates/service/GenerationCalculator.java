package com.gbtec.Templates.service;

import com.gbtec.Templates.model.Generation;

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
