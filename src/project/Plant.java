package project;

import game.Direction;

import java.awt.*;

public class Plant extends Creature {

    public Plant(int x, int y)
    {
        super(x, y);

        this.color = new Color(0, 13, 32);

        this.stayed_health_point = .05;

        this.reproduced_health_point = .7;

        this.reproduced_child_health_point = .1;
    }

    @Override
    protected Creature createAndMoveChild(Direction direction)
    {
        Plant born = new Plant(this.x, this.y);

        born.setHealth(this.getHealth() * this.reproduced_child_health_point);

        born.move(direction);

        return born;
    }
}
