package com.gbtec.service;

import com.gbtec.model.Cell;
import com.gbtec.model.Generation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GenerationReader {

    public static Generation readFromJARFile() {

        InputStream is = GenerationReader.class.getResourceAsStream("/firstGeneration.txt");
        InputStreamReader isr = new InputStreamReader(Objects.requireNonNull(is, "Could not find initialization file, please make sure it exists at the expected path."));
        BufferedReader br = new BufferedReader(isr);

        List<List<Cell>> cells = br.lines().map(line -> Arrays.stream(line.split("")).map(s -> s.equals("1")).map(Cell::new).toList()).toList();

        return new Generation(cells);
    }
}
