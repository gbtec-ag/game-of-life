package com.gbtec;

public class Main {
private static int i = 0;
    public static void main(String[] args) {
        while(i<9){
        Generation generation = new Generation();
        generation.initialize();
        generation.printStatus();
        System.out.println("");
        i++;}


    }

}
