package com.Models;

import com.Helpers.RandomGenerator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Brain {
    private static int initialAction = 4;
    private int currentAction;

    private Mob mob;

    public List<Action> commands = new ArrayList<>();

    public List<String> commandsNames = Arrays.asList(
            "moveUp",
            "moveDown",
            "moveRight",
            "moveLeft",
            "sleep"
    );

    public Brain(Mob mob) {
        for (String commandName: commandsNames) {
            commands.add(new Action(commandName, this.randomJump()));
        }

        this.mob = mob;
        this.currentAction = Brain.initialAction;
    }

    public void processAction() {
        Action action = commands.get(currentAction);

        switch (action.getName()) {
            case "moveUp":
                mob.moveUp();
                break;
            case "moveDown":
                mob.moveDown();
                break;
            case "moveRight":
                mob.moveRight();
                break;
            case "moveLeft":
                mob.moveLeft();
                break;
        }

        updateAction(action.getJump());
    }

    public void updateAction(int jump) {
        currentAction += jump;

        currentAction %= commands.size();
    }

    public int randomJump() {
        return RandomGenerator.nextInt(commandsNames.size());
    }

    protected Brain cloneBrain(Mob mob) {
        Brain newBrain = new Brain(mob);

        newBrain.currentAction = this.currentAction;
        newBrain.commands = new ArrayList<>();

        for (Action action: this.commands)
            newBrain.commands.add(action.cloneAction());

        return newBrain;
    }
}
