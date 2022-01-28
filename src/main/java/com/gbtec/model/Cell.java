package com.gbtec.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class Cell {

    @NonNull
    private Boolean alive;
}
