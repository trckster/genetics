package com.Models;

import com.Enums.ActionSignature;
import com.Helpers.RandomGenerator;
import java.util.ArrayList;
import java.util.List;

public class Brain {
    private static int initialAction = 8;
    private int currentAction;

    private Mob mob;

    public List<Action> commands = new ArrayList<>();

    public Brain(Mob mob) {
        for (ActionSignature commandName: ActionSignature.values()) {
            commands.add(new Action(commandName, this.randomJump()));
        }

        this.mob = mob;
        this.currentAction = Brain.initialAction;
    }

    public void processAction() {
        Action action = commands.get(currentAction);

        switch (action.getName()) {
            case moveUp:
                mob.moveUp();
                break;
            case moveDown:
                mob.moveDown();
                break;
            case moveRight:
                mob.moveRight();
                break;
            case moveLeft:
                mob.moveLeft();
                break;
            case moveRightUp:
                mob.moveRightUp();
                break;
            case moveLeftUp:
                mob.moveLeftUp();
                break;
            case moveRightDown:
                mob.moveRightDown();
                break;
            case moveLeftDown:
                mob.moveLeftDown();
                break;
        }

        updateAction(action.getJump());
    }

    public void updateAction(int jump) {
        currentAction += jump;

        currentAction %= commands.size();
    }

    public int randomJump() {
        return RandomGenerator.nextInt(actionsCount());
    }

    protected Brain cloneBrain(Mob mob) {
        Brain newBrain = new Brain(mob);

        newBrain.currentAction = this.currentAction;
        newBrain.commands = new ArrayList<>();

        for (Action action: this.commands)
            newBrain.commands.add(action.cloneAction());

        return newBrain;
    }

    public int actionsCount() {
        return ActionSignature.values().length;
    }

    protected void mutate() {
        int randomActionIndex = RandomGenerator.nextInt(actionsCount());

        Action action = this.commands.get(randomActionIndex);

        action.setJump(randomJump());
    }
}
