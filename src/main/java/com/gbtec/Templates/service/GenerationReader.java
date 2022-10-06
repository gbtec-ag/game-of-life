package com.gbtec.Templates.service;

import com.gbtec.Templates.model.Generation;

public class GenerationReader {

    public static Generation createInitialGeneration() {
        Generation generation = new Generation();
        generation.addLine("XOOXO");
        generation.addLine("OXOOX");
        generation.addLine("XOXOO");
        generation.addLine("OXOOX");
        generation.addLine("XOOXO");
        return generation;
    }
}
