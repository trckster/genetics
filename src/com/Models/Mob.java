package com.Models;

public class Mob {
    /** Static */
    private static int startingLifeTime = 15;
    private static int lifeTimeBonus = 15;
    private static int breedingFrequency = 20;

    private int x, y, lifeTime, age;

    private Field field;

    private Brain brain;

    public Mob(int x, int y, Field field) {
        this.x = x;
        this.y = y;
        this.lifeTime = startingLifeTime;
        this.field = field;
        this.brain = new Brain(this);
        this.age = 0;
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
        age++;

        if (age % breedingFrequency == 0)
            produceOffspring();
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
        lifeTime += lifeTimeBonus;
    }

    private void produceOffspring() {
        Cell cell = field.getRandomEmptyCellNear(x, y);

        if (cell == null)
            return;

        Mob child = copyMob(cell);
        field.addMob(child);
    }

    private Mob copyMob(Cell cell) {
        Mob newMob = new Mob(cell.getX(), cell.getY(), field);

        newMob.brain = this.brain.cloneBrain(newMob);

        cell.putMob();

        return newMob;
    }
}
