package com.gbtec.snake.implementation;

import com.gbtec.snake.framework.DisplayCell;
import com.gbtec.snake.framework.SnakeCommandProxy;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class SnakeService extends SnakeCommandProxy {

    public SnakeService(SimpMessagingTemplate simpMessagingTemplate) {
        super(simpMessagingTemplate);
    }

    @Override
    public void init() {
        DisplayCell[][] cells = new DisplayCell[16][16];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                cells[i][j] = new DisplayCell(DisplayCell.Type.EMPTY);
            }
        }
        cells[0][0] = new DisplayCell(DisplayCell.Type.SNAKE_BODY, DisplayCell.Orientation.LEFT_RIGHT);
        cells[0][1] = new DisplayCell(DisplayCell.Type.SNAKE_HEAD, DisplayCell.Orientation.LEFT_RIGHT);
        cells[3][5] = new DisplayCell(DisplayCell.Type.FOOD);
        cells[5][5] = new DisplayCell(DisplayCell.Type.WALL);

        updateDisplay(cells);
    }
}
