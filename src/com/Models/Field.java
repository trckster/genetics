package com.Models;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Field extends JPanel {
    private Square[][] squares = new Square[200][200];
    private List<Mob> mobs = new ArrayList<>();

    private int round;
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

        graphics.drawString("Round: " + this.round + ".", 100, 850);
    }

    public void spawnMob(Mob mob) {
        this.squares[mob.getY()][mob.getX()].setColor(Color.BLACK);
        this.mobs.add(mob);
        this.repaint();
    }

    public void nextRound() {
        for (Iterator<Mob> iterator = this.mobs.iterator(); iterator.hasNext();) {
            Mob mob = iterator.next();

            Square oldSquare = this.squares[mob.getY()][mob.getX()];

            mob.act();

            if (mob.isDead())
            {
                iterator.remove();
                oldSquare.setColor(Color.WHITE);
            }

            oldSquare.swapColors(this.squares[mob.getY()][mob.getX()]);

            this.repaint();
        }
        this.repaint();
    }

    public void setRound(int round) {
        this.round = round;
    }
}
