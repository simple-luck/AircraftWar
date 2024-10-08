package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import java.util.LinkedList;
import java.util.List;
import edu.hitsz.application.Main;
import strategy.Strategy;
import strategy.null_shoot;

/*
 * 普通敌机
 * 不可射击
 *
 * @author hitsz
 */
public class MobEnemy extends AbstractEnemy {

    public MobEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        this.setStrategy(new null_shoot());
    }

    @Override
    public void update() {
        vanish();
    }


}
