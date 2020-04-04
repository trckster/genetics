package com.Helpers;

import java.util.Random;

public class RandomGenerator {
    public static int nextInt() {
        return RandomGenerator.nextInt(Integer.MAX_VALUE);
    }

    public static int nextInt(int to) {
        return RandomGenerator.nextInt(0, to - 1);
    }

    public static int nextInt(int from, int to) {
        Random instance = new Random();

        return instance.nextInt(to - from + 1) + from;
    }

    public static boolean betOn(double probability) {
        double gameResult = Math.random();

        return gameResult < probability;
    }
}
