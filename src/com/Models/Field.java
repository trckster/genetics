package com.Models;

import com.Helpers.RandomGenerator;

import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Field extends JPanel {
    public static int maxCellsCount = 200;

    private Cell[][] cells = new Cell[maxCellsCount][maxCellsCount];
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

        this.width = (widthInPixels - squareSize) / this.squareWithBorderSize + 1;
        this.height = (heightInPixels - squareSize) / this.squareWithBorderSize + 1;

        this.generateCells();
    }

    private void generateCells() {
        System.out.println("Generating cells for y: " + height + ", x: " + width);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Cell cell = new Cell(
                        squareSize,
                        x * squareWithBorderSize,
                        y * squareWithBorderSize,
                        x,
                        y,
                        this
                );

                this.cells[x][y] = cell;
            }
        }
    }

    private Cell getRandomEmptyCell() {
        int x = RandomGenerator.nextInt(this.width);
        int y = RandomGenerator.nextInt(this.height);

        while (this.cells[x][y].isNotEmpty()) {
            x = RandomGenerator.nextInt(this.width);
            y = RandomGenerator.nextInt(this.height);
        }

        return this.cells[x][y];
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

    public void spawnRandomMob() {
        Cell cell = this.getRandomEmptyCell();

        Mob mob = cell.spawnMob();

        this.mobs.add(mob);
        this.repaint();
    }

    public void nextRound() {
        for (Iterator<Mob> iterator = this.mobs.iterator(); iterator.hasNext(); ) {
            Mob mob = iterator.next();

            Cell oldCell = this.cells[mob.getX()][mob.getY()];

            System.out.println("Old: " + mob.getY() + " " + mob.getX());

            mob.act();

            if (mob.isDead()) {
                iterator.remove();
                oldCell.setEmpty();
            }

            System.out.println("New: " + mob.getY() + " " + mob.getX());
            Cell newCell = this.cells[mob.getX()][mob.getY()];

            if (oldCell == null)
                System.out.println("Old cell is null");

            if (newCell == null)
                System.out.println("New cell is null");

            oldCell.swapStates(newCell);

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

    public int getCellsWidth() {
        return width;
    }
}
