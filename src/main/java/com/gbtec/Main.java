package com.gbtec;

import com.gbtec.MaxVersion.gameRules;

import static com.gbtec.MaxVersion.presentation.printTheGrid;

public class Main {
    public static void main(String[] args) {
        printTheGrid();
        gameRules.checkRules();
    }

}
