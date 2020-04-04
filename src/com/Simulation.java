package com;

import com.Models.Field;

import javax.swing.*;

public class Simulation {
    private int sleepTimeBetweenRounds = 10;

    private JFrame frame;
    private Field field;
    private int round = 1;

    public Simulation(String name) {
        frame = new JFrame(name);
        this.field = new Field(1920, 800, 5);
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
        this.field.spawnFood();

        this.startEvolution();
    }

    private void spawnMobs() {
        for (int i = 0; i < 15; i++) {
            this.field.spawnRandomMob();
        }
    }

    private void startEvolution() {
        while (round < 10000000) {
            System.out.println("Round " + round + ".");

            try {
                Thread.sleep(sleepTimeBetweenRounds);
            } catch (Exception e) {
                System.out.println("Smth that never happen");
            }

            this.field.setRound(this.round);
            this.field.nextRound();

            this.round += 1;
        }
    }
}
