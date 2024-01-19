package com.gbtec.gameoflife.implementation;

import com.gbtec.gameoflife.framework.GameOfLifeController;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
            throw new RuntimeException("Could not load properties file!", e);
        }
    }

    /**
     * Saves the properties to the GameOfLife.properties file
     */
    public static void saveProperties() {
        try {
            properties.store(new FileOutputStream("src/main/resources/GameOfLife.properties"), null);
        } catch (IOException e) {
            throw new RuntimeException("Could not save properties file!", e);
        }
    }

    /**
     * Returns the value of the given key from the properties file
     *
     * @param key The key for which the value should be returned
     * @return The value of the given key
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Sets the value of the given key in the properties file
     *
     * @param key   The key for which the value should be set
     * @param value The value which should be set
     */
    public static void setProperty(String key, String value) {
        properties.setProperty(key, value);
        saveProperties();
    }


    /**
     * Returns the size of the matrix that should be used to display the generation data
     *
     * @return The size of the matrix
     */
    public static int getDisplayMatrixSize() {
        return Integer.parseInt(properties.getProperty("displayMatrixSize"));
    }

    /**
     * Sets the size of the matrix that should be used to display the generation data
     *
     * @param displayMatrixSize The size of the matrix
     */
    public static void setDisplayMatrixSize(int displayMatrixSize) {
        setProperty("displayMatrixSize", String.valueOf(displayMatrixSize));
    }

    /**
     * Returns true if the display should be infinite (e.g. the left side of the matrix is connected to the right side)
     *
     * @return True if the display should be infinite
     */
    public static boolean getInfiniteDisplay() {
        return Boolean.parseBoolean(properties.getProperty("infiniteDisplay"));
    }

    /**
     * Sets if the display should be infinite (e.g. the left side of the matrix is connected to the right side)
     *
     * @param infiniteDisplay True if the display should be infinite
     */
    public static void setInfiniteDisplay(boolean infiniteDisplay) {
        setProperty("infiniteDisplay", String.valueOf(infiniteDisplay));
    }

    /**
     * Loads the game rules from the properties file
     *
     * @return The game rules
     */
    public static GameOfLifeController.GameRuleData[] getGameRules() {
        String[] rules = properties.getProperty("gameRules").split(";");
        GameOfLifeController.GameRuleData[] gameRules = new GameOfLifeController.GameRuleData[rules.length];
        for (int i = 0; i < rules.length; i++) {
            String[] ruleParts = rules[i].split(",");
            int ruleId = Integer.parseInt(ruleParts[0]);
            boolean enabled = Boolean.parseBoolean(ruleParts[1]);
            boolean nowAlive = Boolean.parseBoolean(ruleParts[2]);
            String operator = ruleParts[3];
            int number = Integer.parseInt(ruleParts[4]);
            boolean alive = Boolean.parseBoolean(ruleParts[5]);
            gameRules[i] = new GameOfLifeController.GameRuleData(ruleId, enabled, nowAlive, operator, number, alive);
        }
        return gameRules;
    }

    /**
     * Returns the enabled game rules
     * @return The enabled game rules
     */
    public static List<GameOfLifeController.GameRuleData> getEnabledGameRules() {
        List<GameOfLifeController.GameRuleData> enabledGameRules = new ArrayList<>();
        Arrays.asList(getGameRules()).forEach(gameRule -> {
            if (gameRule.enabled()) {
                enabledGameRules.add(gameRule);
            }
        });

        return enabledGameRules;
    }

    /**
     * Saves the game rules to the properties file
     * @param gameRules The game rules
     */
    public static void setGameRules(GameOfLifeController.GameRuleData[] gameRules) {
        StringBuilder rules = new StringBuilder();
        for (GameOfLifeController.GameRuleData gameRule : gameRules) {
            rules.append(gameRule.ruleId()).append(",").append(gameRule.enabled()).append(",").append(gameRule.nowAlive()).append(",").append(gameRule.operator()).append(",").append(gameRule.number()).append(",").append(gameRule.alive()).append(";");
        }
        setProperty("gameRules", rules.toString());
    }
}
