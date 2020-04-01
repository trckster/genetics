package com;

import com.Models.Square;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame fr = new JFrame("My simulation");

        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int sqsize = 10;
        int step = sqsize + 5;
        for (int i = 0; i < 500; i += step)
        {
            for (int j = 0; j < 500; j += step)
            {
                Square square = new Square(sqsize, i, j);
                fr.getContentPane().add(square);
            }
        }
        fr.pack();
        fr.setExtendedState(JFrame.MAXIMIZED_BOTH);
        fr.setLocationRelativeTo(null);
        fr.setVisible(true);
    }
}
