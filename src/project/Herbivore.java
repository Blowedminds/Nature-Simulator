package project;

import game.Direction;

import java.awt.*;

public class Herbivore extends Creature {

    private static double move_point = -1;

    public Herbivore(int x, int y)
    {
        super(x, y);

        this.color = new Color(255, 200, 0);

        this.stayed_health_point = -.1;

        this.reproduced_health_point = .4;

        this.reproduced_child_health_point = .2;
    }

    public void attack(Creature attackedCreature)
    {
        attackedCreature.setHealth(attackedCreature.getHealth() / 2 - 1);
    }

    public void move(Direction direction)
    {
        if (direction == Direction.DOWN) {
            this.y++;
        }
        else if(direction == Direction.UP) {
            this.y--;
        }
        else if(direction == Direction.LEFT) {
            this.x++;
        }
        else if(direction == Direction.RIGHT) {
            this.x--;
        }

        this.setHealth(this.getHealth() + this.move_point);
    }

    @Override
    protected Creature createAndMoveChild(Direction direction)
    {
        Herbivore born = new Herbivore(this.x, this.y);

        born.setHealth(this.getHealth()  * this.reproduced_child_health_point);

        born.move(direction);

        return born;
    }
}
