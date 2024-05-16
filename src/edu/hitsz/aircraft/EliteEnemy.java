package edu.hitsz.aircraft;
import edu.hitsz.bullet.BaseBullet;
import java.util.LinkedList;
import java.util.List;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.EnemyBullet;
import strategy.Strategy;
import strategy.direct_shoot;


public class EliteEnemy extends AbstractEnemy {


    public EliteEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        this.setStrategy(new direct_shoot());
        this.direction=1;
    }

    @Override
    public void update() {
        vanish();
    }
}
