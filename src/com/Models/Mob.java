package com.Models;

import com.Helpers.RandomGenerator;

public class Mob {
    private int x, y, age;

    private Field field;

    public Mob(int x, int y, Field field) {
        this.x = x;
        this.y = y;
        this.age = RandomGenerator.nextInt(100);
        this.field = field;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void act() {
        x = (x + 1) % field.getCellsWidth();

        age++;
    }

    public boolean isDead() {
        return age > 100;
    }
}
