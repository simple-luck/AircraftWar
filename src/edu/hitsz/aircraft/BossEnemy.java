package edu.hitsz.aircraft;

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

    /**
     * 子弹射击方向 (向上发射：1，向下发射：-1)
     */
    private int direction = -1;

    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    public List<BaseBullet> shoot() {

                List<BaseBullet> res = new LinkedList<>();
                int x = this.getLocationX();
                int y = this.getLocationY();
                int BULLET_SPEED = 5;
                int speedX = 0;
                int speedY = this.getSpeedY() - direction * 5;
                BaseBullet bullet;
                double angleIncrement = 2 * Math.PI / shootNum;
                for (int i = 0; i < shootNum; i++) {
                    // 子弹发射位置相对飞机位置向前偏移
                    double angle = i * angleIncrement;
                    int Xbullet = (int) (x + 30 * Math.cos(angle));
                    int Ybullet = (int) (y + 30 * Math.sin(angle));
                    speedX = (int) (BULLET_SPEED * Math.cos(angle));
                    speedY = (int) (BULLET_SPEED * Math.sin(angle));
                    bullet = new EnemyBullet(Xbullet, Ybullet, speedX, speedY, power);
                    res.add(bullet);
                }
                return res;

    }
}
