package com.Models;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Field extends JPanel {
    private Square[][] squares = new Square[200][200];

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
                Square square = new Square(this.squareSize, x, y, Color.white);

                this.squares[x / step][y / step] = square;
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
        for (int i = 0; i < 200; i++)
            for (int j = 0; j < 200; j++)
                if (this.squares[i][j] != null)
                    this.squares[i][j].drawVia(graphics);
    }

    public void spawnMob(Mob mob) {
        int acolor = (int)(Math.random() * 255);
        int bcolor = (int)(Math.random() * 255);
        int ccolor = (int)(Math.random() * 255);

        this.squares[mob.getY()][mob.getX()].setColor(new Color(acolor, bcolor, ccolor));
    }
}
