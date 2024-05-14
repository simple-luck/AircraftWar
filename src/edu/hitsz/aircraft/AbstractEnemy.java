package edu.hitsz.aircraft;

public abstract class AbstractEnemy extends AbstractAircraft{
    public AbstractEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }
    public abstract void update();
}
