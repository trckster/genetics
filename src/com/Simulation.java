package com;

import com.Models.Field;
import com.Models.Mob;

import javax.swing.*;
import java.util.Random;

public class Simulation {
    private JFrame frame;
    private Field field;

    public Simulation(String name) {
        frame = new JFrame(name);
        this.field = new Field(1500, 750, 15);
    }

    public void initialize() {
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.frame.getContentPane().add(this.field);

        this.frame.pack();
        this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }

    public void start() {
        this.spawnMobs();
    }

    public void spawnMobs() {
        for (int i = 0; i < 15; i++) {
            int randomX = (int) (Math.random() * 40);
            int randomY = (int) (Math.random() * 40);

            Mob mob = new Mob(randomX, randomY);

            this.field.spawnMob(mob);
            this.frame.repaint();
        }
    }
}
