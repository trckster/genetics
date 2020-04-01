package com;

import com.Models.Field;
import com.Models.Square;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame fr = new JFrame("My simulation");

        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Field field = new Field(500, 500, 10);
        fr.getContentPane().add(field);

        fr.pack();
        fr.setExtendedState(JFrame.MAXIMIZED_BOTH);
        fr.setLocationRelativeTo(null);
        fr.setVisible(true);
    }
}
