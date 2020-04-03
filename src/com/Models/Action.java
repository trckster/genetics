package com.Models;

public class Action {
    private String name;
    private int jump;

    public Action(String name, int jump) {
        this.name = name;
        this.jump = jump;
    }

    public String getName() {
        return name;
    }

    public int getJump() {
        return jump;
    }
}
