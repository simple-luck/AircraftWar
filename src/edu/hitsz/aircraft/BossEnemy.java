package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import strategy.Strategy;
import strategy.circle_shoot;

import java.util.LinkedList;
import java.util.List;

public class BossEnemy extends AbstractAircraft {



    /**
     * 子弹射击方向 (向上发射：-1，向下发射：1)
     */

    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        this.setStrategy(new circle_shoot());
        this.direction=1;
    }

}