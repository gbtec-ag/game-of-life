package com.gbtec.model;

import java.util.ArrayList;
import java.util.Arrays;

import lombok.Data;

@Data
public class Generation {

    private ArrayList<ArrayList<Cell>> cells;

    public Generation() {
        cells = new ArrayList<>();
    }

    public void addLine(String line) {
        final var cellGrid = Arrays.stream(line.split("")).map(character -> character.equals("X")).map(Cell::new).toList();
        cells.add(new ArrayList<>(cellGrid));
    }
}
