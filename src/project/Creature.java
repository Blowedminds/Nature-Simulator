package project;

import game.Direction;
import game.Drawable;
import naturesimulator.Action;
import naturesimulator.LocalInformation;
import ui.GridPanel;

import java.awt.*;
import java.text.NumberFormat;

abstract public class Creature implements Drawable {

    protected Color color;

    protected double stayed_health_point, reproduced_health_point, reproduced_child_health_point, max_health;

    protected int x, y;

    private double health;

    abstract public void attack(Creature attackedCreature);

    abstract public void move(Direction direction);

    abstract public Creature reproduce(Direction direction);

    public void draw(GridPanel panel)
    {
        panel.drawSquare(x, y, this.color);
    }

    public final Action chooseAction(LocalInformation information)
    {
        Direction direction = LocalInformation.getRandomDirection(information.getFreeDirections());

        if(this.shouldReproduce(information)) {

            return new Action(Action.Type.REPRODUCE, direction);
        }
        else if(this.shouldAttack(information)) {

            return new Action(Action.Type.ATTACK, this.findAttackDirection(information));
        }
        else if(this.shouldMove(information)) {

            return new Action(Action.Type.MOVE, direction);
        }

        return new Action(Action.Type.STAY);
    }

    public void stay()
    {
        this.setHealth(this.getHealth() + this.stayed_health_point);
    }

    public double getHealth()
    {
        return this.health;
    }

    public void setHealth(double health)
    {
        health = Math.min(health, this.max_health);

        this.changeColor(health);

        this.health = Math.round(health * 1000.) / 1000.;
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    abstract protected boolean shouldAttack(LocalInformation information);

    abstract protected boolean shouldMove(LocalInformation information);

    abstract protected boolean shouldReproduce(LocalInformation information);

    abstract protected Direction findAttackDirection(LocalInformation information);

    protected Creature reproduceAndMoveChild(Creature born, Direction direction)
    {
        born.setHealth(this.getHealth() * this.reproduced_child_health_point);

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

    private void changeColor(double health)
    {
        double one_third_health = this.max_health / 3;
        double two_third_health = one_third_health * 2;

        if(
            (this.health < one_third_health && health > one_third_health) ||
            (this.health < two_third_health && health > two_third_health)
        ) {
            this.color = this.color.darker();
        }
        else if(
                (this.health > one_third_health && health < one_third_health) ||
                (this.health > two_third_health && health < two_third_health)
        ) {
            this.color = this.color.brighter();
        }
    }
}
