package com.Models;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class Field extends JPanel {
    private List<Rectangle> squares = new ArrayList<Rectangle>();

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
        int step = this.squareSize + 1;

        for (int x = 0; x < this.height; x += step) {
            for (int y = 0; y < this.width; y += step) {
                Rectangle rect = new Rectangle(x, y, this.squareSize, this.squareSize);

                this.squares.add(rect);
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

        Graphics2D g2 = (Graphics2D) g;

        for (Rectangle rectangle: this.squares) {
            g2.draw(rectangle);
        }
    }
}
