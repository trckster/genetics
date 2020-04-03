package com.Models;

public class Mob {
    private int x, y, age;

    private Field field;

    public Mob(int x, int y, Field field) {
        this.x = x;
        this.y = y;
        this.age = (int) (Math.random() * 100);
        this.field = field;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void act() {
        x = (x + 1) % field.getCellsHeight();

        age++;
    }

    public boolean isDead() {
        return age > 100;
    }
}
