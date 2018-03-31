package project;

import game.Direction;
import naturesimulator.Action;
import naturesimulator.LocalInformation;

import java.awt.*;

public class Plant extends Creature {

    public Plant(int x, int y) {
        this.x = x;

        this.y = y;

        this.color = new Color(0, 13, 32);

        this.max_health = 1.;

        this.setHealth(.5);

        this.stayed_health_point = .05;

        this.reproduced_health_point = .7;

        this.reproduced_child_health_point = .1;
    }

    @Override
    public Action chooseAction(LocalInformation information) {

        Direction direction = LocalInformation.getRandomDirection(information.getFreeDirections());

        if(this.shouldReproduce(direction)) {

            return new Action(Action.Type.REPRODUCE, direction);
        }

        return new Action(Action.Type.STAY);
    }

    @Override
    public void attack(Creature attackedCreature) {

    }

    @Override
    public void move(Direction direction) {

    }

    @Override
    public Creature reproduce(Direction direction) {

        Creature child = this.reproduceAndMoveChild(new Plant(this.x, this.y), direction);

        this.setHealth(this.getHealth() * this.reproduced_health_point);

        return child;
    }

    private boolean shouldReproduce(Direction direction)
    {
        return this.getHealth() >= .75 && direction != null;
    }
}
