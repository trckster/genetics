package com;

import com.Models.Field;
import com.Models.Mob;

import javax.swing.*;

public class Simulation {
    private JFrame frame;
    private Field field;
    private int round = 1;

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

        this.startEvolution();
    }

    private void spawnMobs() {
        for (int i = 0; i < 15; i++) {
            int randomX = (int) (Math.random() * 40);
            int randomY = (int) (Math.random() * 40);

            Mob mob = new Mob(randomX, randomY);

            this.field.spawnMob(mob);
        }
    }

    private void startEvolution() {
        while (round < 100000) {
            System.out.println("Round " + round + ".");

            try {
                Thread.sleep(100);
            } catch (Exception e) {
                System.out.println("Smth that never happen");
            }

            this.field.setRound(this.round);
            this.field.nextRound();

            this.round += 1;
        }
    }
}
