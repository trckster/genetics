package com.Models;

import com.Enums.CellState;
import jdk.jshell.spi.ExecutionControl;

import java.awt.*;

public class Cell {
    private int size, x, y;

    private CellState state;

    public Cell(int size, int x, int y) {
        this.size = size;
        this.x = x;
        this.y = y;

        this.state = CellState.EMPTY;
    }

    private Rectangle getAsRectangle() {
        return new Rectangle(this.x, this.y, this.size, this.size);
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

    public void setState(CellState state) {
        this.state = state;
    }
}
