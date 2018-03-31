package project;

import game.Direction;
import game.Drawable;
import naturesimulator.Action;
import naturesimulator.LocalInformation;
import ui.GridPanel;

import java.awt.*;

abstract public class Creature implements Drawable {

    protected Color color;

    protected double stayed_health_point, reproduced_health_point, reproduced_child_health_point;

    protected int x, y;

    protected double max_health;

    private double health;

    public void draw(GridPanel panel)
    {
        panel.drawSquare(x, y, this.color);
    }

    abstract public Action chooseAction(LocalInformation information);

    abstract public void attack(Creature attackedCreature);

    abstract public void move(Direction direction);

    abstract public Creature reproduce(Direction direction);

    public void stay()
    {
        this.health += this.stayed_health_point;
    }

    public double getHealth()
    {
        return this.health;
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    public void setHealth(double health)
    {
        this.health = this.max_health >= health ? health : this.max_health;
    }

    protected Creature reproduceAndMoveChild(Creature born, Direction direction)
    {
        born.setHealth(this.getHealth()  * this.reproduced_child_health_point);

        born.moveToDirection(direction);

        return born;
    }

    protected void moveToDirection(Direction direction)
    {
        if (direction == Direction.DOWN) {
            this.y++;
        }
        else if(direction == Direction.UP) {
            this.y--;
        }
        else if(direction == Direction.LEFT) {
            this.x--;
        }
        else if(direction == Direction.RIGHT) {
            this.x++;
        }
    }
}
