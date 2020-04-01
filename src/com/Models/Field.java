package com.Models;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class Field extends JPanel {
    private List<Square> squares = new ArrayList<Square>();

    private int width;
    private int height;
    private int squareSize;

    public Field(int width, int height, int squareSize) {
        this.width = width;
        this.height = height;
        this.squareSize = squareSize;

        this.generateCells();
    }

    private void generateCells() {
        int step = this.squareSize + 2;

        for (int x = 0; x < this.width; x += step) {
            for (int y = 0; y < this.height; y += step) {
                Square square = new Square(this.squareSize, x, y, Color.getHSBColor(x, y, x + y));

                this.squares.add(square);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.width, this.height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D graphics = (Graphics2D) g;

        graphics.fill(graphics.getClipBounds());
        for (Square square: this.squares) {
            square.drawVia(graphics);
        }
    }
}
