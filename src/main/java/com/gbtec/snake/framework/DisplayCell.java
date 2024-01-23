package com.gbtec.snake.framework;

import lombok.Getter;
import org.slf4j.LoggerFactory;
import reactor.util.annotation.Nullable;

import javax.validation.constraints.NotNull;

@Getter
public class DisplayCell {

    private final Type type;
    private final Orientation orientation;

    public DisplayCell(@NotNull Type type, @Nullable Orientation orientation) {
        if (type == Type.SNAKE_HEAD || type == Type.SNAKE_BODY || type == Type.SNAKE_TAIL) {
            if (orientation == null) {
                throw new IllegalArgumentException("Orientation must not be null for type " + type);
            }
        } else {
            if (orientation != null) {
                LoggerFactory.getLogger(DisplayCell.class).warn("Orientation is ignored for type " + type);
            }
        }


        this.type = type;
        this.orientation = orientation;

    }

    public DisplayCell(@NotNull Type type) {
        this(type, null);
    }

    public enum Type {
        SNAKE_HEAD(UpdateType.UPDATABLE),
        SNAKE_BODY(UpdateType.UPDATABLE),
        SNAKE_TAIL(UpdateType.UPDATABLE),
        FOOD(UpdateType.UPDATABLE),
        WALL(UpdateType.STATIC),
        EMPTY(UpdateType.UPDATABLE);


        public final UpdateType updateType;

        Type(UpdateType updateType) {
            this.updateType = updateType;
        }
    }

    public enum UpdateType {
        /**
         * Can be updated by the user
         */
        UPDATABLE,
        /**
         * Is set before the start of the game and can not be changed during runtime
         */
        STATIC
    }

    public enum Orientation {
        UP_DOWN,
        UP_LEFT,
        UP_RIGHT,

        DOWN_UP,
        DOWN_LEFT,
        DOWN_RIGHT,

        LEFT_UP,
        LEFT_DOWN,
        LEFT_RIGHT,

        RIGHT_UP,
        RIGHT_DOWN,
        RIGHT_LEFT
    }

}
