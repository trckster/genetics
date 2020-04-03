package com.Models;

import com.Helpers.RandomGenerator;

public class Mob {
    private int x, y, age;

    private Field field;

    public Mob(int x, int y, Field field) {
        this.x = x;
        this.y = y;
        this.age = RandomGenerator.nextInt(1);
        this.field = field;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void act() {
        if (canMoveLeft())
            moveLeft();

        age++;
    }

    public boolean canMoveLeft() {
        int newX = x - 1;

        if (newX < 0)
            newX += field.getCellsWidth();

        return field.getCell(newX, y).isEmpty();
    }

    public void moveUp() {
        y++;
    }
    public void moveDown() {
        y--;
    }
    public void moveRight() {
        x = (x + 1) % field.getCellsWidth();
    }
    public void moveLeft() {
        if (--x < 0)
            x += field.getCellsWidth();
    }

    public boolean isDead() {
        return age > 100;
    }
}
