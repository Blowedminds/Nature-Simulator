package project;

import game.Direction;
import game.Drawable;
import naturesimulator.Action;
import naturesimulator.LocalInformation;
import ui.GridPanel;

import java.awt.*;

public class Creature implements Drawable {

    protected Color color;

    protected double stayed_health_point, reproduced_health_point, reproduced_child_health_point;

    protected int x, y;

    private double health = 10;

    public Creature(int x, int y)
    {
        this.x = x;

        this.y = y;
    }

    public void draw(GridPanel panel)
    {
        panel.drawSquare(x, y, this.color);
    }

    public Action chooseAction(LocalInformation information)
    {
        Direction direction = LocalInformation.getRandomDirection(information.getFreeDirections());

        Action action = this instanceof  Herbivore ? new Action(Action.Type.REPRODUCE, direction): new Action(Action.Type.STAY);

        return action;
    }

    public void attack(Creature attackedCreature)
    {

    }

    public void move(Direction direction)
    {

    }

    public Creature reproduce(Direction direction)
    {
        this.setHealth(this.getHealth() * this.reproduced_health_point);

        return this.createAndMoveChild(direction);
    }

    public void stay()
    {
        this.health += this.stayed_health_point;
    }

    public double getHealth()
    {
        return this.health;
    }

    public void setHealth(double health)
    {
        this.health = health;
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    protected Creature createAndMoveChild(Direction direction)
    {
        return null;
    }
}
