package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;

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
    }

    @Override
    public List<BaseBullet> shoot() {

        List<BaseBullet> res = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY();
        int BULLET_SPEED = 14;
        BaseBullet bullet;
        double angleIncrement = 2 * Math.PI / shootNum;
        for (int i = 0; i < shootNum; i++) {
            // 子弹发射位置相对飞机位置向前偏移
            double angle = i * angleIncrement;
            int bullet_speedX = (int) (BULLET_SPEED * Math.cos(angle));
            int bullet_speedY = (int) (BULLET_SPEED * Math.sin(angle));
            bullet = new EnemyBullet(x,y, bullet_speedX, bullet_speedY, power);
            res.add(bullet);
        }
        return res;

    }
}
