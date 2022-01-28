package com.gbtec.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Data;

@Data
public class Generation {

    private final List<List<Cell>> cells;

    public Generation(List<List<Cell>> cells){
        this.cells = cells;
    }

    public Generation() {
        cells = new ArrayList<>();
    }

    public void addLine(String line) {
        final var cellGrid = Arrays.stream(line.split("")).map(character -> character.equals("1")).map(Cell::new).toList();
        cells.add(new ArrayList<>(cellGrid));
    }
}
