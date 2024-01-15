package com.gbtec.gameoflife.implementation;

import com.gbtec.gameoflife.framework.GameOfLifeCommandProxy;
import lombok.Getter;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.Arrays;

/**
 * Getter and Setter for the generation data
 */
@Getter
public class Display extends GameOfLifeCommandProxy {

    private boolean[][] currentGenerationData;

    public Display(SimpMessagingTemplate simpMessagingTemplate) {
        super(simpMessagingTemplate);
    }

    /**
     * Sets the given generation data to the display if the data is valid (see {@link #checkGenerationData(boolean[][])}).
     * @param generationData The generation data that should be set
     */
    public void setGenerationData(boolean[][] generationData) {
        checkGenerationData(generationData);
        this.currentGenerationData = generationData;
        drawGeneration(generationData);
    }

    /**
     * Checks if the given generation data matches the following requirements: <br>
     * - All lines must have the same length <br>
     * - The length and height of the matrix must match (e.g. 16x16) <br>
     * - The length and height of the matrix must match the value set in GameOfLife.properties
     *
     * @param generationData The generation data that should be checked
     */
    public void checkGenerationData(boolean[][] generationData) {
        final int[] currentLength = {-1, -1};

        Arrays.stream(generationData).forEach(x -> {
            if (currentLength[0] == -1) {
                currentLength[0] = x.length;
            } else if (currentLength[0] != x.length) {
                throw new IllegalArgumentException("The given generation data is not valid. All lines must have the same length!");
            }
        });

        currentLength[1] = generationData.length;

        int matrixSize = Integer.parseInt(PropertiesLoader.getProperty("displayMatrixSize"));
        if (currentLength[0] != currentLength[1] && currentLength[0] != matrixSize) {
            throw new IllegalArgumentException(String.format("The given generation data is not valid. The length and height of the matrix must match %sx%s (set in GameOfLife.properties)!", matrixSize, matrixSize));
        }

    }

}
