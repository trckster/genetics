package com.Models;

public class Mob {
    private int x, y;

    public Mob(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void act() {
        this.x = (this.x + 1) % 45;
    }
}
