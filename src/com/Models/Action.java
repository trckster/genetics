package com.Models;

import com.Enums.ActionSignature;

public class Action {
    private ActionSignature name;
    private int jump;

    public Action(ActionSignature name, int jump) {
        this.name = name;
        this.jump = jump;
    }

    public ActionSignature getName() {
        return name;
    }

    public int getJump() {
        return jump;
    }

    public Action cloneAction() {
        return new Action(this.name, this.jump);
    }
}
