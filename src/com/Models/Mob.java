package com.Models;

public class Mob {
    private int x, y, age;

    public Mob(int x, int y) {
        this.x = x;
        this.y = y;
        this.age = (int) (Math.random() * 100);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void act() {
        x = (x + 1) % 45;

        age++;
    }

    public boolean isDead() {
        return age > 100;
    }
}
