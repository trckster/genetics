package com.Models;

import com.Helpers.RandomGenerator;

public class Food {
    private int x, y;

    private Field field;

    public Food(int x, int y, Field field) {
        this.x = x;
        this.y = y;
        this.field = field;
    }
}
