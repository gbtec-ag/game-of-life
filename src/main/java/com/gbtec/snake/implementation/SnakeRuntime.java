package com.gbtec.snake.implementation;

import com.gbtec.snake.framework.DisplayCell;
import com.gbtec.snake.framework.SnakeCommandProxy;
import com.gbtec.snake.framework.SnakeOrientation;
import lombok.Getter;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import reactor.util.annotation.Nullable;

import javax.validation.constraints.NotNull;
import java.util.Random;

public class SnakeRuntime extends SnakeCommandProxy {

    @Getter
    private static boolean isRunning = false;
    @Getter
    private static boolean isInitialized = false;
    @Getter
    private static int speed = 100;
    private static SnakeOrientation currentOrientation = SnakeOrientation.RIGHT;

    private Display display;

    public SnakeRuntime(SimpMessagingTemplate simpMessagingTemplate) {
        super(simpMessagingTemplate);
    }

    public void start() {
        if (isRunning)
            return;

        isRunning = true;
        while (isRunning) {
            DisplayCell[][] newDisplay = generateDisplayUpdate();
            if (newDisplay != null) {
                display.sendDisplayUpdate(newDisplay);
            }

            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void pause() {
        isRunning = false;
    }

    public void changeOrientation(@NotNull SnakeOrientation orientation) {
        currentOrientation = orientation;
    }

    @Nullable
    private DisplayCell[][] generateDisplayUpdate() {
        DisplayCell[][] newDisplay = display.getCurrentDisplay();
        int[] snakeHeadPosition = getPositionOfType(DisplayCell.Type.SNAKE_HEAD);
        SnakeOrientation snakeOrientation = SnakeOrientation.valueOf(newDisplay[snakeHeadPosition[1]][snakeHeadPosition[0]].getOrientation().name().split("_")[1]);
        if (getOppositeOrientation(currentOrientation).equals(snakeOrientation))
            currentOrientation = snakeOrientation;

        DisplayCell.Type cellTypeAhead = getCellTypeAhead();
        switch (cellTypeAhead) {
            case EMPTY -> {
                return moveSnake(display.getCurrentDisplay(), false);
            }
            case FOOD -> {
                increaseSpeed();
                return moveSnake(generateNewFood(), true);
            }
            case WALL, SNAKE_BODY, SNAKE_HEAD, SNAKE_TAIL -> {
                isRunning = false;
                resetGame();
                return null;
            }
            default -> throw new RuntimeException("Unknown cell type: " + cellTypeAhead);
        }
    }

    @NotNull
    private DisplayCell[][] moveSnake(@NotNull DisplayCell[][] newDisplay, boolean isEating) {
        int[] headPosition = getPositionOfType(DisplayCell.Type.SNAKE_HEAD);
        int snakeHeadX = headPosition[0];
        int snakeHeadY = headPosition[1];

        SnakeOrientation orientation = currentOrientation;

        switch (orientation) {
            case UP -> {
                newDisplay[snakeHeadY - 1][snakeHeadX] = new DisplayCell(DisplayCell.Type.SNAKE_HEAD, DisplayCell.Orientation.DOWN_UP);
                DisplayCell.Orientation newTailOrientation = getNewTailOrientation(newDisplay[snakeHeadY][snakeHeadX], newDisplay[snakeHeadY - 1][snakeHeadX]);
                newDisplay[snakeHeadY][snakeHeadX] = new DisplayCell(DisplayCell.Type.SNAKE_BODY, newTailOrientation);
            }
            case DOWN -> {
                newDisplay[snakeHeadY + 1][snakeHeadX] = new DisplayCell(DisplayCell.Type.SNAKE_HEAD, DisplayCell.Orientation.UP_DOWN);
                DisplayCell.Orientation newTailOrientation = getNewTailOrientation(newDisplay[snakeHeadY][snakeHeadX], newDisplay[snakeHeadY + 1][snakeHeadX]);
                newDisplay[snakeHeadY][snakeHeadX] = new DisplayCell(DisplayCell.Type.SNAKE_BODY, newTailOrientation);
            }
            case LEFT -> {
                newDisplay[snakeHeadY][snakeHeadX - 1] = new DisplayCell(DisplayCell.Type.SNAKE_HEAD, DisplayCell.Orientation.RIGHT_LEFT);
                DisplayCell.Orientation newTailOrientation = getNewTailOrientation(newDisplay[snakeHeadY][snakeHeadX], newDisplay[snakeHeadY][snakeHeadX - 1]);
                newDisplay[snakeHeadY][snakeHeadX] = new DisplayCell(DisplayCell.Type.SNAKE_BODY, newTailOrientation);
            }
            case RIGHT -> {
                newDisplay[snakeHeadY][snakeHeadX + 1] = new DisplayCell(DisplayCell.Type.SNAKE_HEAD, DisplayCell.Orientation.LEFT_RIGHT);
                DisplayCell.Orientation newTailOrientation = getNewTailOrientation(newDisplay[snakeHeadY][snakeHeadX], newDisplay[snakeHeadY][snakeHeadX + 1]);
                newDisplay[snakeHeadY][snakeHeadX] = new DisplayCell(DisplayCell.Type.SNAKE_BODY, newTailOrientation);
            }
        }

        if (isEating) {
            return newDisplay;
        }

        int[] tailPosition = getPositionOfType(DisplayCell.Type.SNAKE_TAIL);
        int snakeTailX = tailPosition[0];
        int snakeTailY = tailPosition[1];

        switch (SnakeOrientation.valueOf(newDisplay[snakeTailY][snakeTailX].getOrientation().name().split("_")[1])) {
            case UP ->
                    newDisplay[snakeTailY - 1][snakeTailX] = new DisplayCell(DisplayCell.Type.SNAKE_TAIL, newDisplay[snakeTailY - 1][snakeTailX].getOrientation());
            case DOWN ->
                    newDisplay[snakeTailY + 1][snakeTailX] = new DisplayCell(DisplayCell.Type.SNAKE_TAIL, newDisplay[snakeTailY + 1][snakeTailX].getOrientation());
            case LEFT ->
                    newDisplay[snakeTailY][snakeTailX - 1] = new DisplayCell(DisplayCell.Type.SNAKE_TAIL, newDisplay[snakeTailY][snakeTailX - 1].getOrientation());
            case RIGHT ->
                    newDisplay[snakeTailY][snakeTailX + 1] = new DisplayCell(DisplayCell.Type.SNAKE_TAIL, newDisplay[snakeTailY][snakeTailX + 1].getOrientation());
        }

        newDisplay[snakeTailY][snakeTailX] = new DisplayCell(DisplayCell.Type.EMPTY);

        return newDisplay;
    }

    @NotNull
    private DisplayCell.Orientation getNewTailOrientation(@NotNull DisplayCell currentSnakeHead, @NotNull DisplayCell newSnakeHead) {
        return DisplayCell.Orientation.valueOf(currentSnakeHead.getOrientation().name().split("_")[0] + "_" + newSnakeHead.getOrientation().name().split("_")[1]);
    }

    @NotNull
    private SnakeOrientation getOppositeOrientation(@NotNull SnakeOrientation orientation) {
        return switch (orientation) {
            case UP -> SnakeOrientation.DOWN;
            case DOWN -> SnakeOrientation.UP;
            case LEFT -> SnakeOrientation.RIGHT;
            case RIGHT -> SnakeOrientation.LEFT;
        };
    }

    @NotNull
    private int[] getPositionOfType(@NotNull DisplayCell.Type type) {
        int[] position = new int[2];
        DisplayCell[][] currentDisplay = display.getCurrentDisplay();

        for (int y = 0; y < currentDisplay.length; y++) {
            for (int x = 0; x < currentDisplay[y].length; x++) {
                if (currentDisplay[x][y].getType() == type) {
                    position[0] = y;
                    position[1] = x;
                    break;
                }
            }
        }

        return position;
    }

    @NotNull
    private DisplayCell.Type getCellTypeAhead() {
        DisplayCell[][] currentDisplay = display.getCurrentDisplay();
        int[] position = getPositionOfType(DisplayCell.Type.SNAKE_HEAD);
        int snakeHeadX = position[0];
        int snakeHeadY = position[1];

        return switch (currentOrientation) {
            case UP -> currentDisplay[snakeHeadY - 1][snakeHeadX].getType();
            case DOWN -> currentDisplay[snakeHeadY + 1][snakeHeadX].getType();
            case LEFT -> currentDisplay[snakeHeadY][snakeHeadX - 1].getType();
            case RIGHT -> currentDisplay[snakeHeadY][snakeHeadX + 1].getType();
        };
    }

    private void increaseSpeed() {
        if (speed > 99)
            speed -= 25;
    }

    private DisplayCell[][] generateNewFood() {
        DisplayCell[][] newDisplay = display.getCurrentDisplay();
        boolean isFoodGenerated = false;

        while (!isFoodGenerated) {
            int x = new Random().nextInt(1, newDisplay.length - 1);
            int y = new Random().nextInt(1, newDisplay.length - 1);

            if (!areNeighboursEmpty(x, y))
                continue;

            newDisplay[y][x] = new DisplayCell(DisplayCell.Type.FOOD);
            isFoodGenerated = true;
        }

        return newDisplay;
    }

    private boolean areNeighboursEmpty(int x, int y) {
        DisplayCell[][] currentDisplay = display.getCurrentDisplay();
        return currentDisplay[y][x].getType().equals(DisplayCell.Type.EMPTY) &&
                currentDisplay[y - 1][x].getType().equals(DisplayCell.Type.EMPTY) &&
                currentDisplay[y + 1][x].getType().equals(DisplayCell.Type.EMPTY) &&
                currentDisplay[y][x - 1].getType().equals(DisplayCell.Type.EMPTY) &&
                currentDisplay[y][x + 1].getType().equals(DisplayCell.Type.EMPTY);
    }

    public void resetGame() {
        isRunning = false;
        speed = 750;
        currentOrientation = SnakeOrientation.RIGHT;

        if (!isInitialized()) {
            display = new Display(simpMessagingTemplate);
            isInitialized = true;
        }

        DisplayCell[][] cells = getEmptyDisplay();
        cells[7][2] = new DisplayCell(DisplayCell.Type.SNAKE_TAIL, DisplayCell.Orientation.LEFT_RIGHT);
        cells[7][3] = new DisplayCell(DisplayCell.Type.SNAKE_BODY, DisplayCell.Orientation.LEFT_RIGHT);
        cells[7][4] = new DisplayCell(DisplayCell.Type.SNAKE_HEAD, DisplayCell.Orientation.LEFT_RIGHT);

        cells[7][11] = new DisplayCell(DisplayCell.Type.FOOD);

        display.sendDisplayUpdate(cells);
    }

    @NotNull
    private DisplayCell[][] getEmptyDisplay() {
        DisplayCell[][] cells = new DisplayCell[16][16];
        for (int y = 0; y < cells.length; y++) {
            for (int x = 0; x < cells[y].length; x++) {
                if (x == 0 || x == cells.length - 1 || y == 0 || y == cells.length - 1) {
                    cells[x][y] = new DisplayCell(DisplayCell.Type.WALL);
                } else {
                    cells[x][y] = new DisplayCell(DisplayCell.Type.EMPTY);
                }
            }
        }
        return cells;
    }

}
