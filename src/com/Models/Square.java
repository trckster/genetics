package com.Models;

import java.awt.*;

public class Square {
    private int size, x, y;

    private Color color;

    public Square(int size, int x, int y) {
        this.size = size;
        this.x = x;
        this.y = y;
        this.color = Color.GREEN;
    }

    public Square(int size, int x, int y, Color color) {
        this.size = size;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    private Rectangle getAsRectangle() {
        return new Rectangle(this.x, this.y, this.size, this.size);
    }

    public void drawVia(Graphics2D graphics) {
        graphics.setPaint(this.color);
        graphics.fill(this.getAsRectangle());
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void swapColors(Square square) {
        Color temp = this.color;

        this.color = square.color;
        square.color = temp;
    }
}
