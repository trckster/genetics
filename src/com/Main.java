package com;

public class Main {
    public static void main(String[] args) {
        Simulation simulation = new Simulation("Test simulation");

        simulation.initialize();
        simulation.start();
    }
}
