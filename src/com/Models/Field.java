package com.Models;

import com.Helpers.RandomGenerator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Field extends JPanel {
    private static int maxCellsCount = 20000;
    private static int borderWidth = 1;

    private Cell[][] cells = new Cell[maxCellsCount][maxCellsCount];
    private List<Mob> mobs = new ArrayList<>();
    private List<Mob> kids = new ArrayList<>();
    private int foodCount;

    private int round;
    private int widthInPixels;
    private int heightInPixels;
    private int squareWithBorderSize;
    private int squareSize;

    private int width;
    private int height;

    private int mobsMaxCount = 0, foodMaxCount = 0;

    public Field(int widthInPixels, int heightInPixels, int squareSize) {
        this.widthInPixels = widthInPixels;
        this.heightInPixels = heightInPixels;
        this.squareSize = squareSize;

        this.squareWithBorderSize = squareSize + borderWidth;

        this.width = (widthInPixels - squareSize) / this.squareWithBorderSize + 1;
        this.height = (heightInPixels - squareSize) / this.squareWithBorderSize + 1;

        this.generateCells();
    }

    private void generateCells() {
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

        displayInformationVia(graphics);
    }

    private void displayInformationVia(Graphics2D graphics) {
        graphics.drawString("Round: " + this.round + ".", 100, 850);
        graphics.drawString("Mobs count: " + this.mobs.size() + ".", 100, 875);
        graphics.drawString("Food count: " + this.foodCount + ".", 100, 900);

        graphics.drawString("Max mobs count: " + this.mobsMaxCount + ".", 300, 875);
        graphics.drawString("Max food count: " + this.foodMaxCount + ".", 300, 900);
    }

    public void spawnRandomMob() {
        Cell cell = this.getRandomEmptyCell();

        Mob mob = cell.spawnMob();

        this.mobs.add(mob);
        this.repaint();
    }

    public void spawnFood() {
        int cellsCount = getEmptyCellsCount();

        while (foodCount < (cellsCount / 10)) {
            this.spawnRandomFood();
            cellsCount -= 1;
        }
    }

    public void spawnRandomFood() {
        Cell cell = this.getRandomEmptyCell();

        cell.spawnFood();

        foodCount += 1;
        this.repaint();
    }

    public void nextRound() {
        for (Iterator<Mob> iterator = mobs.iterator(); iterator.hasNext(); ) {
            Mob mob = iterator.next();

            Cell oldCell = cells[mob.getX()][mob.getY()];

            mob.act();

            Cell newCell = cells[mob.getX()][mob.getY()];

            if (newCell.isFood()) {
                foodCount -= 1;

                oldCell.setEmpty();
                newCell.putMob();
                mob.bumpLongevity();

                this.spawnFood();
            } else {
                if (mob.isDead()) {
                    iterator.remove();
                    oldCell.setEmpty();
                }

                oldCell.swapStates(newCell);
            }

            repaint();

        }

        addAllKidsToMobs();

        refreshMaxCounts();

        repaint();
    }

    private void refreshMaxCounts() {
        if (foodMaxCount < foodCount)
            foodMaxCount = foodCount;

        if (mobsMaxCount < mobs.size())
            mobsMaxCount = mobs.size();
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

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void addMob(Mob mob) {
        this.kids.add(mob);
    }

    public Cell getRandomEmptyCellNear(int x, int y) {
        List<Cell> nearCells = getNearCells(x, y);

        List<Cell> availableCells = new ArrayList<>();

        for (Cell cell: nearCells) {
            if (cell.isEmpty())
                availableCells.add(cell);
        }

        if (availableCells.size() == 0)
            return null;

        int randomCellIndex = RandomGenerator.nextInt(availableCells.size());

        return availableCells.get(randomCellIndex);
    }

    public List<Cell> getNearCells(int x, int y) {
        List<Cell> cells = new ArrayList<>();

        if (cellExists(x, y - 1)) cells.add(this.cells[x][y - 1]);
        if (cellExists(x + 1, y - 1)) cells.add(this.cells[x + 1][y - 1]);
        if (cellExists(x + 1, y)) cells.add(this.cells[x + 1][y]);
        if (cellExists(x + 1, y + 1)) cells.add(this.cells[x + 1][y + 1]);
        if (cellExists(x, y + 1)) cells.add(this.cells[x][y + 1]);
        if (cellExists(x - 1, y + 1)) cells.add(this.cells[x - 1][y + 1]);
        if (cellExists(x - 1, y)) cells.add(this.cells[x - 1][y]);
        if (cellExists(x - 1, y - 1)) cells.add(this.cells[x - 1][y - 1]);

        return cells;
    }

    public boolean cellExists(int x, int y) {
        return x < getCellsWidth() && x >= 0 && y < getCellsHeight() && y >= 0;
    }

    public void addAllKidsToMobs() {
        mobs.addAll(kids);

        kids.clear();
    }

    public int getEmptyCellsCount() {
        int emptyCells = 0;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (this.cells[x][y].isEmpty())
                    emptyCells++;
            }
        }

        return emptyCells;
    }
}
