package edu.hitsz.aircraft;
import edu.hitsz.bullet.BaseBullet;
import java.util.LinkedList;
import java.util.List;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.EnemyBullet;
import strategy.Strategy;
import strategy.scatter_shoot;

public class ElitePlusEnemy extends AbstractAircraft{
    private int shootNum = 3;

    /**
     * 子弹伤害
     */
    private int power = 20;

    /**
     * 子弹射击方向 (向上发射：1，向下发射：-1)
     */
    private int direction = -1;


    public ElitePlusEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        setStrategy(new scatter_shoot());
    }

    @Override
    public List<BaseBullet> shoot() {
        return super.shoot();
    }
}
