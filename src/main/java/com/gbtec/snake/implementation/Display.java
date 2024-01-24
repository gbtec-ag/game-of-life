package com.gbtec.snake.implementation;

import com.gbtec.snake.framework.DisplayCell;
import com.gbtec.snake.framework.SnakeCommandProxy;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.Arrays;

public class Display extends SnakeCommandProxy {

    private DisplayCell[][] display;

    public Display(SimpMessagingTemplate simpMessagingTemplate) {
        super(simpMessagingTemplate);
    }

    /**
     * Updates the display if the data is valid (see {@link #checkDisplayData(DisplayCell[][])}).
     * @param display The data to send
     */
    public void sendDisplayUpdate(DisplayCell[][] display) {
        checkDisplayData(display);
        this.display = display;
        updateDisplay(display);
    }

    public DisplayCell[][] getCurrentDisplay() {
        return display;
    }

    public void checkDisplayData(DisplayCell[][] display) {
        int[] currentLength = {-1, -1};

        Arrays.stream(display).forEach(x -> {
            if (currentLength[0] == -1) {
                currentLength[0] = x.length;
            } else if (currentLength[0] != x.length) {
                throw new IllegalArgumentException("The display data is not valid. All rows must have the same length.");
            }
        });

        currentLength[1] = display.length;
        if (currentLength[0] != currentLength[1]) {
            throw new IllegalArgumentException("The display data is not valid. The display must be quadratic.");
        }
    }

}
