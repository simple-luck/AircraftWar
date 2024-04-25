package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import strategy.Strategy;
import strategy.circle_shoot;

import java.util.LinkedList;
import java.util.List;

public class BossEnemy extends AbstractAircraft {


    private int shootNum = 20;

    /**
     * 子弹伤害
     */
    private int power = 20;

    private int direction = -1;

    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        this.setStrategy(new circle_shoot());
    }

    @Override
    public List<BaseBullet> shoot() {
        return super.shoot();
    }
}