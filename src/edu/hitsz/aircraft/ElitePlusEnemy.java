package edu.hitsz.aircraft;
import edu.hitsz.bullet.BaseBullet;
import java.util.LinkedList;
import java.util.List;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.EnemyBullet;
import strategy.Strategy;
import strategy.scatter_shoot;

public class ElitePlusEnemy extends AbstractEnemy{

    public ElitePlusEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        setStrategy(new scatter_shoot());
        this.direction=1;
    }

    @Override
    public void update() {
        decreaseHp(10);
    }

}
