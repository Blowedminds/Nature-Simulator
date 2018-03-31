package project;

import game.Direction;
import naturesimulator.Action;
import naturesimulator.LocalInformation;

import java.awt.*;
import java.util.ArrayList;

public class Herbivore extends Creature {

    private static double move_point = -1;

    public Herbivore(int x, int y)
    {
        this.x = x;

        this.y = y;

        this.color = new Color(255, 200, 0);

        this.max_health = 20.;

        this.setHealth(10.);

        this.stayed_health_point = -.1;

        this.reproduced_health_point = .4;

        this.reproduced_child_health_point = .2;
    }

    @Override
    public Action chooseAction(LocalInformation information) {

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

    @Override
    public void attack(Creature attackedCreature)
    {
        this.setHealth(this.getHealth() + attackedCreature.getHealth());

        attackedCreature.setHealth(0);

        this.x = attackedCreature.getX();

        this.y = attackedCreature.getY();
    }

    @Override
    public void move(Direction direction)
    {
        this.moveToDirection(direction);

        this.setHealth(this.getHealth() + this.move_point);
    }

    @Override
    public Creature reproduce(Direction direction) {

        Creature child = this.reproduceAndMoveChild(new Herbivore(this.x, this.y), direction);

        this.setHealth(this.getHealth() * this.reproduced_health_point);

        return child;
    }

    private boolean shouldReproduce(LocalInformation information)
    {
        return this.getHealth() == this.max_health && !information.getFreeDirections().isEmpty();
    }

    private boolean shouldMove(LocalInformation information) {

        return this.getHealth() - this.move_point > 0 && !information.getFreeDirections().isEmpty();
    }

    private Direction findAttackDirection(LocalInformation information) {

        ArrayList<Direction> directions = new ArrayList<>();


        if(information.getCreatureDown() instanceof Plant) {
            directions.add(Direction.DOWN);
        }
        if (information.getCreatureLeft() instanceof Plant) {
            directions.add(Direction.LEFT);
        }
        if (information.getCreatureRight() instanceof Plant) {
            directions.add(Direction.RIGHT);
        }
        if (information.getCreatureUp() instanceof Plant) {
            directions.add(Direction.UP);
        }

        return LocalInformation.getRandomDirection(directions);
    }

    private boolean shouldAttack(LocalInformation information) {

        if(information.getCreatureDown() instanceof Plant) {
            return true;
        }
        else if (information.getCreatureLeft() instanceof Plant) {
            return true;
        }
        else if (information.getCreatureRight() instanceof Plant) {
            return true;
        }
        else if (information.getCreatureUp() instanceof Plant) {
            return true;
        }

        return false;
    }
}
