package com.Models;

import com.Enums.CellState;

import java.awt.*;

public class Cell {
    private int size, pixelX, pixelY;
    private int x ,y;

    private CellState state;

    private Field field;

    public Cell(int size, int pixelX, int pixelY, int x, int y, Field field) {
        this.size = size;
        this.pixelX = pixelX;
        this.pixelY = pixelY;

        this.x = x;
        this.y = y;

        this.state = CellState.EMPTY;
        this.field = field;
    }

    private Rectangle getAsRectangle() {
        return new Rectangle(this.pixelX, this.pixelY, this.size, this.size);
    }

    public void drawVia(Graphics2D graphics) {
        graphics.setPaint(this.getColor());
        graphics.fill(this.getAsRectangle());
    }

    public Color getColor() {
        switch (state) {
            case MOB:
                return Color.BLUE;
            case FOOD:
                return Color.GREEN;
            default:
                return Color.WHITE;
        }
    }

    public void swapStates(Cell cell) {
        CellState temp = this.state;

        this.state = cell.state;
        cell.state = temp;
    }

    public void setEmpty() {
        this.state = CellState.EMPTY;
    }

    public Mob spawnMob() {
        this.putMob();

        return new Mob(this.x, this.y, this.field);
    }

    public Food spawnFood() {
        this.state = CellState.FOOD;

        return new Food(this.x, this.y, this.field);
    }

    public void putMob() {
        this.state = CellState.MOB;
    }

    public boolean isNotEmpty() {
        return this.state != CellState.EMPTY;
    }

    public boolean containsMob() {
        return this.state == CellState.MOB;
    }

    public boolean isFood() {
        return this.state == CellState.FOOD;
    }
}
