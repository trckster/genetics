package com.Models;

public class Mob {
    private int x, y, lifeTime;

    private Field field;

    private Brain brain;

    public Mob(int x, int y, Field field) {
        this.x = x;
        this.y = y;
        this.lifeTime = 100;
        this.field = field;
        this.brain = new Brain(this);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void act() {
        brain.processAction();

        lifeTime--;
    }

    public boolean canMove(int newX, int newY) {
        if (newY < 0 || newY >= field.getCellsHeight())
            return false;

        return !field.getCell(newX, newY).containsMob();
    }

    public void moveUp() {
        if (canMove(x, y - 1))
            y--;
    }
    public void moveDown() {
        if (canMove(x, y + 1))
            y++;
    }
    public void moveRight() {
        int newX = (x + 1) % field.getCellsWidth();

        if (canMove(newX, y))
            x = newX;
    }
    public void moveLeft() {
        int newX = x - 1;

        if (newX < 0)
            newX += field.getCellsWidth();

        if (canMove(newX, y))
            x = newX;
    }

    public boolean isDead() {
        return lifeTime < 0;
    }

    public void bumpLongevity() {
        lifeTime += 10;
    }
}
