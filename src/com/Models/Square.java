package com.Models;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Square extends JPanel {
    private int size;

    Rectangle rectangle;

    Square (int size, int x, int y) {
        this.size = size;

        this.rectangle = new Rectangle(x, y, this.size, this.size);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.size, this.size);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.draw(this.rectangle);
    }
}
