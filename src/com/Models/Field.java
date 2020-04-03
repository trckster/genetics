package com.Models;

import com.Enums.CellState;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Field extends JPanel {
    private Cell[][] cells = new Cell[200][200];
    private List<Mob> mobs = new ArrayList<>();

    private int round;
    private int widthInPixels;
    private int heightInPixels;
    private int squareWithBorderSize;
    private int squareSize;

    private int width;
    private int height;

    public Field(int widthInPixels, int heightInPixels, int squareSize) {
        this.widthInPixels = widthInPixels;
        this.heightInPixels = heightInPixels;
        this.squareSize = squareSize;

        this.squareWithBorderSize = squareSize + 2;

        this.width = widthInPixels / this.squareWithBorderSize;
        this.height = heightInPixels / this.squareWithBorderSize;

        this.generateCells();
    }

    private void generateCells() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Cell cell = new Cell(squareSize, x * squareWithBorderSize, y * squareWithBorderSize);

                this.cells[x][y] = cell;
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.widthInPixels, this.heightInPixels);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D graphics = (Graphics2D) g;

        graphics.fill(graphics.getClipBounds());

        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                this.cells[i][j].drawVia(graphics);

        graphics.drawString("Round: " + this.round + ".", 100, 850);
    }

    public void spawnMob(Mob mob) {
        this.cells[mob.getY()][mob.getX()].setState(CellState.MOB);
        this.mobs.add(mob);
        this.repaint();
    }

    public void nextRound() {
        for (Iterator<Mob> iterator = this.mobs.iterator(); iterator.hasNext(); ) {
            Mob mob = iterator.next();

            Cell oldCell = this.cells[mob.getY()][mob.getX()];

            mob.act();

            if (mob.isDead()) {
                iterator.remove();
                oldCell.setEmpty();
            }

            oldCell.swapStates(this.cells[mob.getY()][mob.getX()]);

            this.repaint();
        }
        this.repaint();
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getCellsHeight() {
        return height;
    }
}
