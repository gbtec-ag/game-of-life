package com.gbtec.gameoflife.implementation;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This class should be used to load the properties from the GameOfLife.properties file.@
 **/
public class PropertiesLoader {

    /**
     * Properties object which contains the properties from the GameOfLife.properties file
     */
    private static final Properties properties = new Properties();

    /**
     * Loads the properties from the GameOfLife.properties file
     */
    public static void loadProperties() {
        try {
            properties.load(new FileInputStream("src/main/resources/GameOfLife.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Could not load properties file!");
        }
    }

    /**
     * Returns the value of the given key from the properties file
     * @param key The key for which the value should be returned
     * @return The value of the given key
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }


    /**
     * Returns the size of the matrix that should be used to display the generation data
     * @return The size of the matrix
     */
    public static int getDisplayMatrixSize() {
        return Integer.parseInt(properties.getProperty("displayMatrixSize"));
    }

}
